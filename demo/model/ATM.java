package group20.example.demo.model;

import java.util.HashMap;
import java.util.Map;

public class ATM {
	private String atmId;                    // Mã máy ATM
    private double availableCash;            // Số tiền còn trong máy
    private boolean isOperational;           // Trạng thái hoạt động
    private String location;                 // Vị trí đặt máy
    private Map<String, Transaction> transactionHistory;  // Lịch sử giao dịch

    public ATM(String atmId, double initialCash, String location) {
        this.atmId = atmId;
        this.availableCash = initialCash;
        this.isOperational = true;
        this.location = location;
        this.transactionHistory = new HashMap<>();
    }

    /**
     * Kiểm tra máy còn đủ tiền không
     */
    public boolean hasEnoughCash(double amount) {
        return availableCash >= amount;
    }

    /**
     * Phát tiền cho khách
     */
    public boolean dispenseCash(double amount) {
        if (!hasEnoughCash(amount)) return false;
        availableCash -= amount;
        return true;
    }

    /**
     * Nạp tiền vào máy ATM
     */
    public void addCash(double amount) {
        if (amount > 0) availableCash += amount;
    }

    /**
     * Đặt trạng thái hoạt động cho máy
     */
    public void setOperational(boolean operational) {
        isOperational = operational;
    }

    /**
     * Lấy lịch sử giao dịch
     */
    public Map<String, Transaction> getTransactionHistory() {
        return new HashMap<>(transactionHistory);
    }

	public String getAtmId() {
		return atmId;
	}

	public double getAvailableCash() {
		return availableCash;
	}

	public String getLocation() {
		return location;
	}
	

}
