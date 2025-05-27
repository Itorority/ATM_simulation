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

    /**
     * Thực hiện chuyển tiền từ tài khoản hiện tại tới tài khoản người nhận
     * 
     * @param senderUserId          ID người gửi (userId)
     * @param pin                   Mã PIN xác nhận giao dịch
     * @param recipientAccountNumber Số tài khoản người nhận
     * @param amount                Số tiền cần chuyển
     * @throws RuntimeException     Nếu giao dịch thất bại (ví dụ: sai PIN, số dư không đủ)
     */
    
    public void transfer(long senderUserId, String pin, String recipientAccountNumber, double amount) {
    	// Gọi service để thực hiện chuyển tiền, nếu có lỗi sẽ ném exception
        accountService.updateBalanceByAccountNumber(senderUserId, pin, recipientAccountNumber, amount);

        // Nếu chuyển tiền thành công, lấy lại số dư mới nhất của tài khoản gửi
        BigDecimal updatedBalance = accountService.findAccountById(senderUserId).getBalance();
        currentAccount.setBalance(updatedBalance); // Kích hoạt ChangeListener
    }
}
