package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class AccountDaoIntTest {

  @Autowired
  private AccountDao accountDao;

  @Autowired
  private TraderDao traderDao;

  private Account savedAccount;

  @Before
  public void insertOne() {
    Trader trader = new Trader();
    trader.setId(1);
    trader.setDob(new Date());
    trader.setFirst_name("Luna");
    trader.setLast_name("Lovegood");
    trader.setCountry("Canada");
    trader.setEmail("miggles@hogwarts.com");
    traderDao.save(trader);

    savedAccount = new Account();
    savedAccount.setId(1);
    savedAccount.setTraderId(1);
    savedAccount.setAmount(100.00d);
    accountDao.save(savedAccount);
  }

  @After
  public void deleteOne() {
    accountDao.deleteById(savedAccount.getId());
  }

  @Test
  public void finalAllById() {
    List<Account> accounts = Lists
        .newArrayList(accountDao.findAllById(Arrays.asList(savedAccount.getId(), -1)));
    assertEquals(1, accounts.size());
    assertEquals(savedAccount.getAmount(), accounts.get(0).getAmount());
  }

  @Test
  public void updateOne() {
    savedAccount.setAmount(200.00d);
    Account updatedReturn = accountDao.save(savedAccount);
    assertEquals(savedAccount.getAmount(), updatedReturn.getAmount());
  }
}
