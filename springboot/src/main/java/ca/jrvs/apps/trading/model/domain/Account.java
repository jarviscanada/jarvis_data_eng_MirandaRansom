package ca.jrvs.apps.trading.model.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Account{
  @Id
  @GeneratedValue
  private Integer id;
  private Integer traderId;
  private Double amount;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getTraderId() {
    return traderId;
  }

  public void setTraderId(Integer traderId) {
    this.traderId = traderId;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }
}
