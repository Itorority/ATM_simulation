package group20.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.context.ApplicationContext;

import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;
import group20.example.demo.service.AccountService;

@Controller
public class PINController {
	private AccountController accountController;
	private UserController userController;

	public PINController(AccountController accountController, UserController userController) {
		super();
		this.accountController = accountController;
		this.userController = userController;
	}

	public AccountModel changePIN(AccountModel currentAccount, String newPIN) {
		accountController.changePIN(currentAccount.getUserId(), newPIN);
		return accountController.findAccountById(currentAccount.getUserId());
	}

}
