package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityOrderDao extends JdbcCrudDao<SecurityOrder>{

  private static final Logger logger = LoggerFactory.getLogger(SecurityOrder.class);

  private final String TABLE_NAME = "security_order";
  private final String ID_COLUMN = "id";

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public SecurityOrderDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME)
        .usingGeneratedKeyColumns(ID_COLUMN);
  }

  @Override
  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  @Override
  public SimpleJdbcInsert getSimpleJdbcInsert() {
    return simpleJdbcInsert;
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  public String getIdColumnName() {
    return ID_COLUMN;
  }

  @Override
  Class<SecurityOrder> getEntityClass() {
    return SecurityOrder.class;
  }

  /**
   * helper method that updates one quote
   *
   * @param entity
   */
  @Override
  public int updateOne(SecurityOrder entity) {
    String updateSql = "UPDATE " + TABLE_NAME +
        " SET account_id=" + entity.getAccountId() +
        ", status='" + entity.getStatus() +
        "', ticker='" + entity.getTicker() +
        "', size=" + entity.getSize() +
        ", price=" + entity.getPrice() +
        ", notes='" + entity.getNotes() +
        "' WHERE " + ID_COLUMN + "=" + entity.getId();
    return jdbcTemplate.update(updateSql);
  }

  @Override
  public <S extends SecurityOrder> Iterable<S> saveAll(Iterable<S> iterable) {
    List<S> list = new ArrayList<>();
    for (S order : iterable) {
      list.add(save(order));
    }
    return list;
  }

  @Override
  public void delete(SecurityOrder securityOrder) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends SecurityOrder> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
