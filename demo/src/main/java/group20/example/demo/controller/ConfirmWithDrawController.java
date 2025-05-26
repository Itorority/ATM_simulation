package group20.example.demo.controller;

import org.springframework.stereotype.Controller;

import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;

@Controller
public class ConfirmWithDrawController {
	
	private UserController userController;
	private AccountController accountController;
	
	public ConfirmWithDrawController(UserController userController, AccountController accountController) {
		super();
		this.userController = userController;
		this.accountController = accountController;
	}

	// xác nhận rút tiền và số tiền mới của tài khoản sau khi rút
	public AccountModel confirmWithDraw(UserModel currentUser, AccountModel currentAccount, double amount) {
		userController.withdrawMoney(currentUser.getUserId(), currentAccount.getPinHash(), amount);
		return accountController.findAccountById(currentUser.getUserId());
 
	}
}