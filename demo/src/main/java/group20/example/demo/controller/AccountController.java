package group20.example.demo.controller;

import org.springframework.stereotype.Controller;

import group20.example.demo.entity.Account;
import group20.example.demo.mapper.EntityModelMapper;
import group20.example.demo.model.AccountModel;
import group20.example.demo.service.AccountService;

@Controller
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    
    // Kiểm tra account có tồn tại hay không
    public boolean isAccountExist(Long userId) {
        return accountService.isAccountExist(userId);
    }

    // Dùng ID tìm account
    public AccountModel findAccountById(Long userId) {
        Account accountEntity = accountService.findAccountById(userId);
        return EntityModelMapper.toAccountModel(accountEntity);
    }

    // Cập nhật số dư account bằng UserID
    public void updateBalanceByUserId(Long userId, String pin, double balance) {
        accountService.updateBalanceByUserId(userId, pin, balance);
    }

    // Cập nhật số dư Account bằng AccountNumber
    public void updateBalanceByAccountNumber(long userId, String pin, String accountNumber, double money) {
        accountService.updateBalanceByAccountNumber(userId, pin, accountNumber, money);
    }
}
