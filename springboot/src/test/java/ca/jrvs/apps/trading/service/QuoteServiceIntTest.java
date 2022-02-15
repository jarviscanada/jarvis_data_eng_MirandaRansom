package ca.jrvs.apps.trading.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class QuoteServiceIntTest {

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private QuoteDao quoteDao;

  @Before
  public void setup() {
    quoteDao.deleteAll();
  }

  @Test
  public void findIexQuoteByTicker() {
    IexQuote iexQuote = quoteService.findIexQuoteByTicker("AAPL");
    assertEquals(iexQuote.getSymbol(), "AAPL");
  }

  @Test
  public void updateMarketData() {
    List<Quote> quotes = quoteService.saveQuotes(new ArrayList<String>(Arrays.asList("AAPL", "FB", "MSFT")));
    List<Quote> before = quoteService.findAllQuotes();
    quoteService.updateMarketData();
    List<Quote> after = quoteService.findAllQuotes();

    assertNotEquals(before, after);
  }

  @Test
  public void saveQuotes() {
    List<Quote> quotes = quoteService.saveQuotes(new ArrayList<String>(Arrays.asList("AAPL", "FB", "MSFT")));
    assertEquals(3, quotes.size());
    assertEquals("AAPL", quotes.get(0).getTicker());
  }

  @Test
  public void saveQuote() {
    Quote quote = quoteService.saveQuote("FB");
    assertEquals("FB", quote.getTicker());
  }

  @Test
  public void findAllQuotes() {
    quoteService.saveQuotes(new ArrayList<String>(Arrays.asList("AAPL", "FB", "MSFT")));
    List<Quote> tableQuotes = quoteService.findAllQuotes();
    assertEquals(3, tableQuotes.size());
    assertEquals("AAPL", tableQuotes.get(0).getTicker());
  }
}
