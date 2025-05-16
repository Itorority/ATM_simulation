package group20.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "atm")
public class ATM {

  @Column(name = "amount")
  private BigDecimal amount;

  @Id
  @Column(name = "atm_id")
  private Integer atmId;

  @Column(name = "atm_status")
  private String atmStatus;

  // Constructors
  public ATM() {
  }

  public ATM(BigDecimal amount, Integer atmId, String atmStatus) {
    this.amount = amount;
    this.atmId = atmId;
    this.atmStatus = atmStatus;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Integer getAtmId() {
    return atmId;
  }

  public void setAtmId(Integer atmId) {
    this.atmId = atmId;
  }

  public String getAtmStatus() {
    return atmStatus;
  }

  public void setAtmStatus(String atmStatus) {
    this.atmStatus = atmStatus;
  }
}
