package group20.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import group20.example.demo.entity.Transaction;
import group20.example.demo.model.TransactionModel;
import group20.example.demo.mapper.EntityModelMapper;
import group20.example.demo.service.TransactionService;

@Controller
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Lưu giao dịch mới.
     */
    public Transaction saveTransaction(Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    /**
     * Lấy danh sách TransactionModel của một tài khoản (mới nhất trước).
     */
    public List<TransactionModel> getTransactionModelsByAccountNumber(String accountNumber, int quantity) {
        List<Transaction> transactions = transactionService.getAllTransactionsByAccountNumber(accountNumber, quantity);
        return EntityModelMapper.toTransactionModelList(transactions);
    }
}
