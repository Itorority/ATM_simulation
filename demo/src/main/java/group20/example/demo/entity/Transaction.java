// package group20.example.demo.entity;

// import jakarta.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "transactions")
// public class Transaction {

// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// @Column(name = "transaction_id")
// private Long transactionId;

// @Column(name = "account_number")
// private String accountNumber;

// @Column(name = "transaction_type")
// private String transactionType;

// @Column(name = "date_created")
// private LocalDateTime dateCreated;

// @Column(name = "description")
// private String description;

// // Constructors
// public Transaction() {
// }

// public Transaction(String accountNumber, String transactionType,
// LocalDateTime dateCreated, String description) {
// this.accountNumber = accountNumber;
// this.transactionType = transactionType;
// this.dateCreated = dateCreated;
// this.description = description;
// }

// // Getters and setters
// public Long getTransactionId() {
// return transactionId;
// }

// public void setTransactionId(Long transactionId) {
// this.transactionId = transactionId;
// }

// public String getAccountNumber() {
// return accountNumber;
// }

// public void setAccountNumber(String accountNumber) {
// this.accountNumber = accountNumber;
// }

// public String getTransactionType() {
// return transactionType;
// }

// public void setTransactionType(String transactionType) {
// this.transactionType = transactionType;
// }

// public LocalDateTime getDateCreated() {
// return dateCreated;
// }

// public void setDateCreated(LocalDateTime dateCreated) {
// this.dateCreated = dateCreated;
// }

// public String getDescription() {
// return description;
// }

// public void setDescription(String description) {
// this.description = description;
// }

// // toString()
// @Override
// public String toString() {
// return "Transaction{" +
// "transactionId=" + transactionId +
// ", accountNumber='" + accountNumber + '\'' +
// ", transactionType='" + transactionType + '\'' +
// ", dateCreated=" + dateCreated +
// ", description='" + description + '\'' +
// '}';
// }
// }
