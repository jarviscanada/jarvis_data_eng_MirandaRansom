package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public abstract class JdbcCrudDao<T extends Entity<Integer>> implements CrudRepository<T, Integer> {

  private static final Logger logger = LoggerFactory.getLogger(JdbcCrudDao.class);

  abstract public JdbcTemplate getJdbcTemplate();

  abstract public SimpleJdbcInsert getSimpleJdbcInsert();

  abstract public String getTableName();

  abstract public String getIdColumnName();

  abstract Class<T> getEntityClass();

  /**
   * Save an entity and update auto-generated integer ID
   * @param entity to be saved
   * @return save entity
   */
  @Override
  public<S extends  T>S save (S entity) {
    if (existsById(entity.getId())) {
      if (updateOne(entity) != 1) {
        throw new DataRetrievalFailureException("Unable to update quote");
      }
    } else {
      addOne(entity);
    }
    return entity;
  }

  /**
   * helper method that saves one quote
   */
  private<S extends T> void addOne(S entity) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);

    Number newId = getSimpleJdbcInsert().executeAndReturnKey(parameterSource);
    entity.setId(newId.intValue());
  }

  /**
   * helper method that updates one quote
   */
  abstract public int updateOne(T entity);

  /**
   * finds entity by id
   * @param id of desired entity
   * @return entity associated with the id
   */
  @Override
  public Optional<T> findById(Integer id) {
    Optional<T> entity = Optional.empty();
    String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + "=?";

    try {
      entity = Optional.ofNullable((T) getJdbcTemplate()
          .queryForObject(selectSql, BeanPropertyRowMapper.newInstance(getEntityClass()), id));
    } catch (IncorrectResultSizeDataAccessException e) {
      logger.debug("Can't find trader id:" + id, e);
    }
    return entity;
  }

  @Override
  public boolean existsById(Integer id) {
    Optional<T> object = findById(id);
    return object.isPresent();
  }

  @Override
  public List<T> findAll() {
    String selectSql = "SELECT * FROM " + getTableName();
    return getJdbcTemplate().query(selectSql, BeanPropertyRowMapper.newInstance(getEntityClass()));
  }

  @Override
  public List<T> findAllById(Iterable<Integer> ids) {
    List<T> list = new ArrayList<>();

    for (Integer id : ids) {
      Optional<T> object = findById(id);
      object.ifPresent(list::add);
    }
    return list;
  }

  @Override
  public void deleteById(Integer id) {
    String deleteSql = "DELETE FROM " + getTableName() + " WHERE " +
        getIdColumnName() + "=" + id;
    getJdbcTemplate().update(deleteSql);
  }

  @Override
  public long count() {
    String countSql = "SELECT COUNT(" + getIdColumnName() + ") FROM " +
        getTableName();
    Long count = getJdbcTemplate().queryForObject(countSql,
        new Object[] {}, Long.class);
    return count;
  }

  @Override
  public void deleteAll() {
    String deleteSql = "DELETE FROM " + getTableName();
    getJdbcTemplate().update(deleteSql);
  }
}
