package group20.example.demo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionModel {
    private Long transactionId;
    private String accountNumber;
    private TransactionType type;
    private double amount;
    private String recipientAccount;
    private LocalDateTime timestamp;
    private String status;
    private String failureReason;
    private String description;

    public enum TransactionType {
        DEPOSIT,        
        WITHDRAW,      
        TRANSFER,       
        BALANCE_CHECK   
    }

    // Constructor đầy đủ
    public TransactionModel(Long transactionId, String accountNumber, TransactionType type, LocalDateTime timestamp, String description) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.type = type;
        this.timestamp = timestamp;
        this.description = description;
    }
    // Các getter và setter (nếu cần chỉnh sửa)

    public void complete() {
        this.status = "completed";
    }

    public void fail(String reason) {
        this.status = "failed";
        this.failureReason = reason;
    }

    // Getters
    public Long getTransactionId() { return transactionId; }

    public String getAccountNumber() { return accountNumber; }

    public TransactionType getType() { return type; }

    public double getAmount() { return amount; }

    public String getRecipientAccount() { return recipientAccount; }

    public LocalDateTime getTimestamp() { return timestamp; }

    public String getStatus() { return status; }

    public String getFailureReason() { return failureReason; }

    public String getDescription() { return description; }

    // Dùng để hiển thị rõ ràng trong giao diện JComboBox
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String timeStr = timestamp.format(formatter);

        return String.format("[%s] %s - %.2f VND - %s", timeStr, type, amount,
                status.equals("failed") ? failureReason : status.toUpperCase());
    }
}
