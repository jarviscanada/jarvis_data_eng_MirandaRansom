package ca.jrvs.apps.trading.model.domain;

import java.util.Date;

public class TraderAccountView {

  private Trader trader;
  private Account account;

  public TraderAccountView(Trader trader, Account account) {
    this.trader = trader;
    this.account = account;
  }

  public Trader getTrader() {
    return trader;
  }

  public void setTrader(Trader trader) {
    this.trader = trader;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Integer getTraderId() {
    return trader.getId();
  }

  public void setTraderId(Integer traderId) {
    this.trader.setId(traderId);
  }

  public String getFirst_name() {
    return trader.getFirst_name();
  }

  public void setFirst_name(String first_name) {
    this.trader.setFirst_name(first_name);
  }

  public String getLast_name() {
    return trader.getLast_name();
  }

  public void setLast_name(String last_name) {
    this.trader.setLast_name(last_name);
  }

  public Date getDob() {
    return trader.getDob();
  }

  public void setDob(Date dob) {
    this.trader.setDob(dob);
  }

  public String getCountry() {
    return trader.getCountry();
  }

  public void setCountry(String country) {
    this.trader.setCountry(country);
  }

  public String getEmail() {
    return trader.getEmail();
  }

  public void setEmail(String email) {
    this.trader.setEmail(email);
  }

  public Integer getAccountId() {
    return account.getId();
  }

  public void setAccountId(Integer accountId) {
    this.account.setId(accountId);
  }

  public Double getAmount() {
    return account.getAmount();
  }

  public void setAmount(Double amount) {
    this.account.setAmount(amount);
  }
}
