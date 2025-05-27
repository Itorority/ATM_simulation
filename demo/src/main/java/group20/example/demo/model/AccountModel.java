package group20.example.demo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountModel {

    private Long userId;

    private String accountNumber;

    private String pinHash;

    private BigDecimal balance;
    
    private final List<ChangeListener> listeners = new ArrayList<>();

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
        notifyBalanceChanged();
    }
    

    public void addBalanceChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    private void notifyBalanceChanged() {
        for (ChangeListener listener : listeners) {
            listener.onBalanceChanged(this.balance);
        }
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
