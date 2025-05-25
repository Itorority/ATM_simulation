package group20.example.demo.model;

import java.math.BigDecimal;

public class AccountModel {

    private Long userId;

    private String accountNumber;

    private String pinHash;

    private BigDecimal balance;

    // Constructors
    public AccountModel() {
    }

    public AccountModel(Long userId, String accountNumber, String pinHash, BigDecimal balance) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.pinHash = pinHash;
        this.balance = balance;
    }

    // Getters & Setters
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

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", accountNumber='" + accountNumber + '\'' +
                ", pinHash='" + pinHash + '\'' +
                ", balance=" + balance +
                '}';
    }
}
