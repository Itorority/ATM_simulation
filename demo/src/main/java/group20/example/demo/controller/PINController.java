package group20.example.demo.controller;

import org.springframework.stereotype.Controller;

import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;
import group20.example.demo.service.AccountService;

@Controller
public class PINController {
	private ConfirmWithDrawController confirmWithDrawController;
	private UserController userController;
	private AccountController accountController;
	private AccountService accountService;

	public PINController(ConfirmWithDrawController confirmWithDrawController, UserController userController,
			AccountController accountController, AccountService accountService) {
		super();
		this.confirmWithDrawController = confirmWithDrawController;
		this.userController = userController;
		this.accountController = accountController;
		this.accountService = accountService;
	}

	public AccountModel verifyPIN(UserModel currentUser, AccountModel currentAccount, String inputPIN, double amount) {
		if(!inputPIN.equals(currentAccount.getPinHash())) {
			throw new IllegalArgumentException("Mã PIN không đúng. Vui lòng thử lại.");
		}
		return confirmWithDrawController.confirmWithDraw(currentUser, currentAccount, amount);
	}
	
}
