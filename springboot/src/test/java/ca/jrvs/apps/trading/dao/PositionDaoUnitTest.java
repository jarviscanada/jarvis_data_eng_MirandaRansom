package ca.jrvs.apps.trading.dao;


import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Date;
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
public class PositionDaoUnitTest {

  @Autowired
  private PositionDao positionDao;

  @Autowired
  private SecurityOrderDao securityOrderDao;

  @Autowired
  private TraderDao traderDao;

  @Autowired
  private AccountDao accountDao;

  @Autowired
  private QuoteDao quoteDao;

  @Before
  public void setup() {
    Trader trader  = new Trader();
    trader.setId(1);
    trader.setFirst_name("Lola");
    trader.setLast_name("Loka");
    trader.setEmail("bunny123@hotmail.com");
    trader.setCountry("Ireland");
    trader.setDob(new Date());
    traderDao.save(trader);

    Quote quote = new Quote();
    quote.setAskPrice(10d);
    quote.setAskSize(10);
    quote.setBidPrice(10.2d);
    quote.setBidSize(10);
    quote.setId("aapl");
    quote.setLastPrice(10.1d);
    quoteDao.save(quote);

    Account account = new Account();
    account.setId(1);
    account.setTrader_id(1);
    account.setAmount(100.00d);
    accountDao.save(account);

    SecurityOrder savedSecurityOrder = new SecurityOrder();
    savedSecurityOrder.setId(1);
    savedSecurityOrder.setAccountId(1);
    savedSecurityOrder.setTicker("aapl");
    savedSecurityOrder.setPrice(10.0d);
    savedSecurityOrder.setSize(10);
    savedSecurityOrder.setStatus("FILLED");
    securityOrderDao.save(savedSecurityOrder);
  }

  @Test
  public void testExistsById() {
    assertTrue(positionDao.exsistsById("1"));
  }
}
