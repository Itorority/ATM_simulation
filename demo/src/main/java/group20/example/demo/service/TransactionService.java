package group20.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import group20.example.demo.entity.Transaction;

@Service
public class TransactionService {
  public static List<Transaction> transactionsData = new ArrayList<>();
  public static final TransactionService transactionService = new TransactionService();

  public static TransactionService getInstance() {
    if (transactionService == null) {
      return new TransactionService();
    }
    return transactionService;
  }

  /**
   * @param transactionRepository
   */
  public TransactionService() {
  }

  public static void insertData() {

    transactionsData.add(new Transaction(
        "1000000001",
        "deposit",
        LocalDateTime.now(),
        "Nạp 100.0000 VND vào tài khoản"));

    transactionsData.add(new Transaction(
        "1000000002",
        "withdraw",
        LocalDateTime.now(),
        "Rút tiền 100.000 VND"));

    transactionsData.add(new Transaction(
        "1000000003",
        "transfer",
        LocalDateTime.now(),
        "Chuyển khoản 100.000 VND vào tài khoản số 1000000003"));

    transactionsData.add(new Transaction(
        "1000000004",
        "deposit",
        LocalDateTime.now(),
        "Nạp 100.0000 VND vào tài khoản"));

    transactionsData.add(new Transaction(
        "1000000005",
        "withdraw",
        LocalDateTime.now(),
        "Rút tiền 100.000 VND"));
  }

  /**
   * save transactionn down to database with Transaction entity
   * 
   * @param transaction
   * @return
   */
  public void saveTransaction(Transaction transaction) {
    // return transactionRepository.save(transaction);
    this.transactionsData.add(transaction);

  }

  /**
   * select and print all transaction in database with accounntn number
   * 
   * @param accountNumber
   * @return
   */
  public List<Transaction> getAllTransactionsByAccountNumber(String accountNumber, int quantity) {
    // Pageable pageable = PageRequest.of(0, quantity); // Lấy 'quantity' giao dịch
    // gần nhất
    // return transactionRepository.findTopNByAccountNumber(accountNumber,
    // pageable);
    return transactionsData.stream()
        .filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
        .limit(quantity)
        .toList();
  }
}