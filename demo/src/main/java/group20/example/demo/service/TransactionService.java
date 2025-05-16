package group20.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import group20.example.demo.entity.Transaction;
import group20.example.demo.repo.TransantionRepository;

@Service
public class TransactionService {
  @Autowired
  private TransantionRepository transactionRepository;

  /**
   * @param transactionRepository
   */
  public TransactionService(TransantionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  /**
   * save transactionn down to database with Transaction entity
   * 
   * @param transaction
   * @return
   */
  public Transaction saveTransaction(Transaction transaction) {
    return transactionRepository.save(transaction);
  }

  /**
   * select and print all transaction in database with accounntn number
   * 
   * @param accountNumber
   * @return
   */
  public List<Transaction> getAllTransactionsByAccountNumber(String accountNumber, int quantity) {
    Pageable pageable = PageRequest.of(0, quantity); // Lấy 'quantity' giao dịch gần nhất
    return transactionRepository.findTopNByAccountNumber(accountNumber, pageable);
  }
}