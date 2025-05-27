package group20.example.demo.controller;

import java.math.BigDecimal;

import group20.example.demo.model.AccountModel;
import group20.example.demo.service.AccountService;

public class MoneyTransferController {

    private final AccountService accountService;
    private final AccountModel currentAccount;

    public MoneyTransferController(AccountService accountService, AccountModel currentAccount) {
        this.accountService = accountService;
        this.currentAccount = currentAccount;
    }

    public void transfer(long senderUserId, String pin, String recipientAccountNumber, double amount) {
        // Perform the transfer (throws exception if something fails)
        accountService.updateBalanceByAccountNumber(senderUserId, pin, recipientAccountNumber, amount);

        // After successful transfer, update local model to reflect new balance
        BigDecimal updatedBalance = accountService.findAccountById(senderUserId).getBalance();
        currentAccount.setBalance(updatedBalance); // triggers BalanceChangeListener in MainForm
    }
}
