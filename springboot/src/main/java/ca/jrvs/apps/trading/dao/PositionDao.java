package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDao {

  private static final Logger logger = LoggerFactory.getLogger(PositionDao.class);

  private final String TABLE_NAME = "position";
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public PositionDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public Boolean exsistsById(String id) {
    Boolean hasOpenPosition = !findAllById(id).isEmpty();
    return hasOpenPosition;
  }

  public List<Position> findAllById(String id) {
    String selectSql = "SELECT * FROM " + TABLE_NAME +
        " WHERE account_id=" + id;
    return this.jdbcTemplate.query(selectSql, BeanPropertyRowMapper.newInstance(Position.class));
  }
}
