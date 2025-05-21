package group20.example.demo.model;

import java.time.LocalDateTime;

public class Account {
	private String accountNumber; // Số tài khoản
	private String accountHolder; // Tên chủ tài khoản
	private double balance; // Số dư tài khoản
	private String pin; // Mã PIN
	private LocalDateTime createdAt; // Thời gian tạo tài khoản
	private boolean isLocked; // Trạng thái khóa tài khoản
	private int failedAttempts; // Số lần nhập sai PIN

	public Account(String accountNumber, String accountHolder, double balance, String pin) {
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.balance = balance;
		this.pin = pin;
		this.createdAt = LocalDateTime.now();
		this.isLocked = false;
		this.failedAttempts = 0;
	}

	/**
	 * Kiểm tra mã PIN nhập vào
	 * nam mô adi da phat
	 */
	public boolean checkPin(String enteredPin) {
		if (enteredPin.equals(this.pin)) {
			this.failedAttempts = 0;
			return true;
		}
		this.failedAttempts++;
		if (this.failedAttempts >= 3) {
			this.isLocked = true;
		}
		return false;
	}

	/**
	 * Gửi tiền vào tài khoản
	 */
	public boolean deposit(double amount) {
		if (amount <= 0)
			return false;
		this.balance += amount;
		return true;
	}

	/**
	 * Rút tiền từ tài khoản
	 */
	public boolean withdraw(double amount) {
		if (amount <= 0 || amount > this.balance || this.isLocked)
			return false;
		this.balance -= amount;
		return true;
	}

	/**
	 * Đổi mã PIN
	 */
	public boolean changePin(String oldPin, String newPin) {
		if (checkPin(oldPin) && newPin != null && newPin.length() >= 4) {
			this.pin = newPin;
			return true;
		}
		return false;
	}

	/**
	 * Chuyển khoản sang tài khoản khác
	 */
	public boolean transfer(Account toAccount, double amount) {
		if (this.withdraw(amount)) {
			toAccount.deposit(amount);
			return true;
		}
		return false;
	}

	// Getter & Setter
	public double getBalance() {
		return this.balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public int getFailedAttempts() {
		return failedAttempts;
	}

	public void lockAccount() {
		this.isLocked = true;
	}

	public void unlockAccount() {
		this.isLocked = false;
		this.failedAttempts = 0;
	}
}
