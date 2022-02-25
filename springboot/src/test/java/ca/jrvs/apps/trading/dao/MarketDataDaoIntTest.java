package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import ca.jrvs.apps.trading.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;

public class MarketDataDaoIntTest {

  private MarketDataDao dao;

  @Before
  public void init() {
    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    cm.setMaxTotal(50);
    cm.setDefaultMaxPerRoute(50);
    MarketDataConfig marketDataConfig = new MarketDataConfig();
    marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));
    marketDataConfig.setHost("https://cloud.iexapis.com/v1");

    dao = new MarketDataDao(cm, marketDataConfig);
  }

  @Test
  public void findIexQuotesByTickers() throws IOException {
    //happy path
    List<IexQuote> quoteList = dao.findAllById(Arrays.asList("AAPL", "FB"));
    assertEquals(2, quoteList.size());
    assertEquals("AAPL", quoteList.get(0).getSymbol());

    //fail path
    try {
      dao.findAllById(Arrays.asList("AAPL", "FB2"));
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void findByTicker() {
    String ticker = "AAPL";
    IexQuote iexQuote = dao.findById(ticker).get();
    assertEquals(ticker, iexQuote.getSymbol());
  }
}
