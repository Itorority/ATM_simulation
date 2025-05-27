package group20.example.demo.controller;

import javax.swing.JFrame;

import org.springframework.context.ApplicationContext;

import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;
import group20.example.demo.view.FormFactory;
import group20.example.demo.view.FormType;
import group20.example.demo.view.IForm;

public class MainController {
    private final ApplicationContext context;
    private final UserModel user;
    private final AccountModel account;

    public MainController(ApplicationContext context, UserModel user, AccountModel account) {
        this.context = context;
        this.user = user;
        this.account = account;
    }

    // Mở form rút tiền, truyền vào JFrame hiện tại để chuyển đổi màn hình
    public void openWithdrawForm(JFrame currentFrame) {
        openForm(FormType.WITHDRAW, currentFrame);
    }

    // Mở form nạp tiền
    public void openDepositForm(JFrame currentFrame) {
        openForm(FormType.DEPOSIT, currentFrame);
    }

    // Mở form chuyển tiền
    public void openTransferForm(JFrame currentFrame) {
        openForm(FormType.TRANSFER, currentFrame);
    }

    // Mở form thay đổi mã PIN
    public void openPinForm(JFrame currentFrame) {
        openForm(FormType.PIN, currentFrame);
    }

    // Mở form xem thông tin cá nhân
    public void openProfileForm(JFrame currentFrame) {
        openForm(FormType.PROFILE, currentFrame);
    }

    // Mở form xem lịch sử giao dịch
    public void openTransactionHistoryForm(JFrame currentFrame) {
        openForm(FormType.HISTORY, currentFrame);
    }

    // Hàm hỗ trợ mở form theo kiểu FormType
    private void openForm(FormType type, JFrame currentFrame) {
        // Tạo form mới dựa theo loại form, truyền context, user, account
        IForm form = FormFactory.createForm(type, context, user, account);
        if (form instanceof JFrame frame) {
            frame.setLocation(currentFrame.getLocation());
            frame.setVisible(true);
            currentFrame.dispose();
        }
    }

    // Cập nhật số dư tài khoản
    public void updateBalance(double newBalance) {
        AccountController accountController = context.getBean(AccountController.class);

        Long userId = user.getUserId();
        String pin = account.getPinHash();

        // Cập nhật số dư tài khoản trong cơ sở dữ liệu dựa trên userId và pin
        accountController.updateBalanceByUserId(userId, pin, newBalance);

        // Lấy thông tin tài khoản mới cập nhật từ DB
        AccountModel updatedAccount = accountController.findAccountById(userId);

        // Nếu có thông tin tài khoản mới, cập nhật số dư trong account hiện tại để đồng bộ
        if (updatedAccount != null) {
            account.setBalance(updatedAccount.getBalance());
        }
    }
}
