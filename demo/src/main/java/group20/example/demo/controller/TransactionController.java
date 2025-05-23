package group20.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import group20.example.demo.entity.Transaction;
import group20.example.demo.service.TransactionService;

@Controller
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Save a transaction by delegating to the service.
     */
    public Transaction saveTransaction(Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    /**
     * Get transactions by account number, limited by quantity.
     */
    public List<Transaction> getTransactionsByAccountNumber(String accountNumber, int quantity) {
        return transactionService.getAllTransactionsByAccountNumber(accountNumber, quantity);
    }
}