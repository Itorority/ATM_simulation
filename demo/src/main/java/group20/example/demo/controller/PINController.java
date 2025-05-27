
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

	/**
	 * Xác minh mã PIN người dùng nhập vào và thực hiện rút tiền nếu mã PIN đúng
	 * @param currentUser: người dùng hiện tại 
	 * @param currentAccount: tài khoản của người dùng
	 * @param inputPIN: mã PIN người dùng nhập vào
	 * @param amount: số tiền muốn rút
	 * @throws IllegalArgumentException nếu mã PIn không khớp với tài khoản
	 */
	public AccountModel verifyPIN(UserModel currentUser, AccountModel currentAccount, String inputPIN, double amount) {
		// So sánh mã PIN người dùng nhập với mã PIN được lưu trong tài khoản
		if(!inputPIN.equals(currentAccount.getPinHash())) {
			// Nếu không trùng khớp, dừng giao dịch, yêu cầu nhập lại
			throw new IllegalArgumentException("Mã PIN không đúng. Vui lòng thử lại.");
		}
		// Nếu mã PIN khớp, tiếp tục thực hiện rút tiền
		return confirmWithDrawController.confirmWithDraw(currentUser, currentAccount, amount);
	}
	
	public AccountModel changePIN(AccountModel currentAccount, String newPIN) {
		accountController.changePIN(currentAccount.getUserId(), newPIN);
		return accountController.findAccountById(currentAccount.getUserId());
	}

}
