package group20.example.demo.controller;

import group20.example.demo.entity.Account;
import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;
import group20.example.demo.service.AccountService;
import group20.example.demo.service.UserService;

public class DepositController {

    private UserService userService;
    private AccountService accountService;

    public DepositController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    /**
     * Thực hiện xác nhận nạp tiền và cập nhật số dư mới cho tài khoản
     * @param currentUser người dùng hiện tại
     * @param currentAccount tài khoản đang sử dụng 
     * @param amount số tiền muốn nạp
     * @return AccountModel: tài khoản đã được cập nhật số dư mới
     */
    public AccountModel confirmDeposit(UserModel currentUser, AccountModel currentAccount, double amount) {
        // Gọi phương thức nạp tiền (có thể không cần PIN nếu đã xác thực trước)
        userService.depositMoney(currentAccount.getUserId(), currentAccount.getPinHash(), amount);

        // Sau khi nạp, lấy lại thông tin tài khoản mới
        Account updatedAccount = accountService.findAccountById(currentAccount.getUserId());

        // Cập nhật số dư mới cho AccountModel hiện tại
        currentAccount.setBalance(updatedAccount.getBalance());

        return currentAccount;
    }
}
