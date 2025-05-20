package group20.example.demo.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "account_number")
  private String accountNumber;

  @Column(name = "pin_hash")
  private String pinHash;

  /**
   * @param userId
   * @param accountNumber
   * @param pinHash
   * @param balance
   */
  public Account(Long userId, String accountNumber, String pinHash, BigDecimal balance) {
    this.userId = userId;
    this.accountNumber = accountNumber;
    this.pinHash = pinHash;
    this.balance = balance;
  }

  @Column(name = "balance")
  private BigDecimal balance;

  // Constructors
  public Account() {
  }

  public Account(String accountNumber, String pinHash, BigDecimal balance) {
    this.accountNumber = accountNumber;
    this.pinHash = pinHash;
    this.balance = balance;
  }

  // Getters and Setters
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getPinHash() {
    return pinHash;
  }

  public void setPinHash(String pinHash) {
    this.pinHash = pinHash;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  // toString()
  @Override
  public String toString() {
    return "User{" +
        "userId=" + userId +
        ", accountNumber='" + accountNumber + '\'' +
        ", pinHash='" + pinHash + '\'' +
        ", balance=" + balance +
        '}';
  }
}
