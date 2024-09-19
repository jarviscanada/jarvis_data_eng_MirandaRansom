package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.Optional;
import java.util.function.Consumer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDaoIntTest {

  @Autowired
  private QuoteDao quoteDao;
  private Quote savedQuote = new Quote();

  @Before
  public void insertOne() {
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setId("aapl");
    savedQuote.setLastPrice(10.1d);
    quoteDao.save(savedQuote);
  }

  @After
  public void deleteOne() {
    quoteDao.deleteById(savedQuote.getId());
  }

  @Test
  public void testFindById() {
    Optional<Quote> returnValue = quoteDao.findById("aapl");
    Assert.assertNotEquals(Optional.empty(), returnValue);
  }

  @Test
  public void testUpdateValues() {
    savedQuote.setBidPrice(10.5d);
    Quote returnValue = quoteDao.save(savedQuote);
    assertEquals(savedQuote.getBidPrice(), returnValue.getBidPrice());
  }

  @Test
  public void testDeleteById() {
    quoteDao.deleteById("aapl");
    assertFalse(quoteDao.existsById("aapl"));
  }

  @Test
  public void testCount() {
    assertEquals(1, quoteDao.count());
  }

  @Test
  public void testDeleteAll() {
    quoteDao.deleteAll();
    assertEquals(0, quoteDao.count());
  }

  @Test
  public void testFindAll() {
    quoteDao.findAll().forEach(new Consumer<Quote>() {
      @Override
      public void accept(Quote quote) {
        assertEquals("aapl", quote.getTicker());
      }
    });
  }
}
