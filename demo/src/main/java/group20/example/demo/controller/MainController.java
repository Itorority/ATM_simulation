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

    public void openWithdrawForm(JFrame currentFrame) {
        IForm form = FormFactory.createForm(FormType.WITHDRAW, context, user, account);
        openForm(form, currentFrame);
    }

    public void openDepositForm(JFrame currentFrame) {
        IForm form = FormFactory.createForm(FormType.DEPOSIT, context, user, account);
        openForm(form, currentFrame);
    }

    public void openTransferForm(JFrame currentFrame) {
        IForm form = FormFactory.createForm(FormType.TRANSFER, context, user, account);
        openForm(form, currentFrame);
    }

    public void openPinForm(JFrame currentFrame) {
        IForm form = FormFactory.createForm(FormType.PIN, context, user, account);
        openForm(form, currentFrame);
    }

    public void openProfileForm(JFrame currentFrame) {
        IForm form = FormFactory.createForm(FormType.PROFILE, context, user, account);
        openForm(form, currentFrame);
    }

    private void openForm(IForm form, JFrame currentFrame) {
        if (form instanceof JFrame frame) {
            frame.setLocation(currentFrame.getLocation());
            frame.setVisible(true);
            currentFrame.dispose();
        }
    }
    
    public void updateBalance(double newBalance) {
        AccountController accountController = context.getBean(AccountController.class);

        Long userId = user.getUserId(); 
        String pin = account.getPinHash();

        accountController.updateBalanceByUserId(userId, pin, newBalance);

        AccountModel updatedAccount = accountController.findAccountById(userId);
        account.setBalance(updatedAccount.getBalance());
    }

}
