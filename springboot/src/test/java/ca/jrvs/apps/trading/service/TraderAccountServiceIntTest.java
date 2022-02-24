package ca.jrvs.apps.trading.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
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
public class TraderAccountServiceIntTest {

  private TraderAccountView savedView;
  @Autowired
  private TraderAccountService traderAccountService;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private AccountDao accountDao;

  @Before
  public void testCreateTraderAccountView(){
    Trader newTrader = new Trader();
    newTrader.setFirst_name("John");
    newTrader.setLast_name("Kingsly");
    newTrader.setEmail("kingsmen24@gmail.com");
    newTrader.setDob(new Date());
    newTrader.setCountry("Canada");

    savedView = traderAccountService.createTraderAndAccount(newTrader);
    assertEquals(newTrader.getFirst_name(), savedView.getFirst_name());
    assertEquals(Double.valueOf(0), savedView.getAmount());

    savedView.setAmount(100.00d);
    accountDao.saveAndFlush(savedView.getAccount());
  }

  @Test
  public void testDeleteTrader() {
    //unable to delete
    try {
      traderAccountService.deleteTraderById(savedView.getTraderId());
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    //happy path
    traderAccountService.withdraw(savedView.getTraderId(), savedView.getAmount());
    traderAccountService.deleteTraderById(savedView.getTraderId());
    assertFalse(traderDao.existsById(savedView.getTraderId()));
    assertFalse(accountDao.existsById(savedView.getTraderId()));
  }

  @Test
  public void testDeposit() {
    Account updatedAccount = traderAccountService.deposit(savedView.getTraderId(), 50.00);
    Double expected = savedView.getAmount() + 50.00;
    assertEquals(expected, updatedAccount.getAmount());
  }

  @Test
  public void testWithdraw() {
    //happy path
    Account updatedAccount = traderAccountService.withdraw(savedView.getTraderId(), 50.00);
    Double expected = savedView.getAmount() - 50.00;
    assertEquals(expected, updatedAccount.getAmount());

    //insufficient funds
    try {
      traderAccountService.withdraw(savedView.getTraderId(), 150.00);
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
  }
}
