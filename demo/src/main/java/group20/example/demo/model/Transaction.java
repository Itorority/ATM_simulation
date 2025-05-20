package group20.example.demo.model;

import java.time.LocalDateTime;

public class Transaction {
	private String transactionId;        // Mã giao dịch
    private String accountNumber;        // Số tài khoản thực hiện giao dịch
    private TransactionType type;        // Loại giao dịch
    private double amount;               // Số tiền giao dịch
    private String recipientAccount;     // Số tài khoản người nhận (nếu chuyển khoản)
    private LocalDateTime timestamp;     // Thời gian giao dịch
    private String status;               // Trạng thái giao dịch
    private String failureReason;        // Lý do thất bại (nếu có)
    private String description;          // Mô tả giao dịch

    public enum TransactionType {
        DEPOSIT,        // Nạp tiền
        WITHDRAWAL,     // Rút tiền
        TRANSFER,       // Chuyển khoản
        BALANCE_CHECK   // Kiểm tra số dư
    }

    public Transaction(String accountNumber, TransactionType type, double amount, String recipientAccount, String description) {
        this.transactionId = "TXN" + System.currentTimeMillis();
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.recipientAccount = recipientAccount;
        this.timestamp = LocalDateTime.now();
        this.description = description;
        this.status = "pending";
    }

    /**
     * Đánh dấu giao dịch hoàn thành
     */
    public void complete() { this.status = "completed"; }

    /**
     * Đánh dấu giao dịch thất bại
     */
    public void fail(String reason) {
        this.status = "failed";
        this.failureReason = reason;
    }

	public String getTransactionId() {
		return transactionId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public TransactionType getType() {
		return type;
	}

	public double getAmount() {
		return amount;
	}

	public String getRecipientAccount() {
		return recipientAccount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getStatus() {
		return status;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public String getDescription() {
		return description;
	}
    


}
