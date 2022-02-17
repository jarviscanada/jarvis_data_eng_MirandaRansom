package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class SecurityOrderDaoIntTest {

  @Autowired
  private SecurityOrderDao securityOrderDao;

  @Autowired
  private TraderDao traderDao;

  @Autowired
  private AccountDao accountDao;

  @Autowired
  private QuoteDao quoteDao;

  private SecurityOrder savedSecurityOrder;

  @Before
  public void insertOne() {
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

    savedSecurityOrder = new SecurityOrder();
    savedSecurityOrder.setId(1);
    savedSecurityOrder.setAccountId(1);
    savedSecurityOrder.setTicker("aapl");
    savedSecurityOrder.setPrice(10.0d);
    savedSecurityOrder.setSize(10);
    savedSecurityOrder.setStatus("Processed");
    securityOrderDao.save(savedSecurityOrder);
  }

  @After
  public void deleteOne() {
    securityOrderDao.deleteById(savedSecurityOrder.getId());
  }

  @Test
  public void findAllById() {
    List<SecurityOrder> orders = Lists.
        newArrayList(securityOrderDao.findAllById(Arrays.asList(savedSecurityOrder.getId(), -1)));
    assertEquals(1, orders.size());
    assertEquals(savedSecurityOrder.getAccountId(), orders.get(0).getAccountId());
  }

  @Test
  public void updateOne() throws IllegalAccessException {
    savedSecurityOrder.setNotes("filled");
    securityOrderDao.save(savedSecurityOrder);
    SecurityOrder updatedReturn = securityOrderDao.findById(savedSecurityOrder.getId())
        .orElseThrow(IllegalAccessException::new);
    assertEquals(savedSecurityOrder.getNotes(), updatedReturn.getNotes());
  }

}
