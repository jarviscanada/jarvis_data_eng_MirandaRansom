package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

  private final Logger logger = LoggerFactory.getLogger(QuoteDao.class);

  private static final String TABLE_NAME = "quote";
  private static final String ID_COLUMN_NAME = "ticker";

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public QuoteDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
  }
/**
 * @throws DataAccessException for unexpected SQL result or SQL execution failure
 */
  @Override
  public Quote save(Quote quote) {
    if (existsById(quote.getTicker())) {
      int updatedRowNo = updateOne(quote);

      if (updatedRowNo != 1) {
        throw new DataRetrievalFailureException("Unable to update quote");
      }
    } else {
      addOne(quote);
    }
    return quote;
  }

  /**
   * helper method that saves one quote
   * @param quote to be saved
   */
  private void addOne(Quote quote) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
    int row = simpleJdbcInsert.execute(parameterSource);
    if (row != 1) {
    throw  new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
    }
  }

  /**
   * helper method that updates one quote
   * @param quote to be updated
   * @return int
   */
  private int updateOne(Quote quote) {
    String update_sql = "UPDATE quote SET last_price=?, bid_price=?, "
        + "bid_size=?, ask_price=?, ask_size=? WHERE ticker=?";
    return jdbcTemplate.update(update_sql, makeUpdateValues(quote));
  }

  /**
   * helper method that makes sql update values object
   * @param quote to be updated
   * @return UPDATE_SQL values
   */
  private Object[] makeUpdateValues(Quote quote) {
    return new Object[] {quote.getLastPrice(), quote.getBidPrice(),
      quote.getBidSize(), quote.getAskPrice(), quote.getAskSize(),quote.getTicker()};
  }

  @Override
  public <S extends Quote> Iterable<S> saveAll(Iterable<S> iterable) {
    for (S item: iterable) {
      save(item);
    }
    return iterable;
  }

  /**
   * return all quotes
   * @throws DataAccessException if failed to update
   */
  @Override
  public Iterable<Quote> findAll() {
    String stmt = "SELECT * FROM " + TABLE_NAME;
    return this.jdbcTemplate.query(stmt, BeanPropertyRowMapper.newInstance(Quote.class));
  }


  @Override
  public void deleteAll() {
    String stmt = "DELETE FROM " + TABLE_NAME;
    this.jdbcTemplate.update(stmt);
  }

  /**
   * find a quote by ticker
   * @param ticker name
   * @return quote or Optional.empty if not found
   */
  @Override
  public Optional<Quote> findById(String ticker) {
    Quote quote = null;
    String stmt = "SELECT * FROM " + TABLE_NAME +
        " WHERE " + ID_COLUMN_NAME + " =?";

    try {
      quote = (Quote) this.jdbcTemplate.queryForObject(stmt, new BeanPropertyRowMapper(Quote.class), ticker);
    } catch (EmptyResultDataAccessException e) {
      logger.debug("Can't find ticker: {}", ticker, e);
      return Optional.empty();
    }
    if (quote == null) {
      throw new RuntimeException("Resource not found");
    }
    return Optional.of(quote);
  }

  @Override
  public boolean existsById(String s) {
    Optional<Quote> quote = findById(s);
    return quote.isPresent();
  }

  @Override
  public void deleteById(String id) {
    String stmt = "DELETE FROM " + TABLE_NAME + " WHERE ticker=?";
    this.jdbcTemplate.update(stmt, id);
  }

  @Override
  public long count() {
    String countSql = "SELECT COUNT(ticker) FROM " +
        TABLE_NAME;
    Long count = this.jdbcTemplate.queryForObject(countSql,
        new Object[] {}, Long.class);
    return count;
  }

  @Override
  public void delete(Quote quote) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends Quote> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public Iterable<Quote> findAllById(Iterable<String> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
