package group20.example.demo.controller;

import org.springframework.stereotype.Controller;

import group20.example.demo.entity.Account;
import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;
import group20.example.demo.service.AccountService;
import group20.example.demo.service.UserService;

@Controller
public class ConfirmWithDrawController {
	
	private UserService userService;
	private AccountService accountService;
	
	public ConfirmWithDrawController(UserService userService, AccountService accountService) {
		super();
		this.userService = userService;
		this.accountService = accountService;
	}
	
	/**
	 * Thực hiện xác nhận rút tiền và cập nhật số dư mới cho tài khoản
	 * @param currentUser: người dùng hiện tại
	 * @param currentAccount: tài khoản đang sử dụng 
	 * @param amount: số tiền muốn rút
	 * @return AccountModel: tài khoản đã được cập nhật số dư mới
	 */
	public AccountModel confirmWithDraw(UserModel currentUser, AccountModel currentAccount, double amount) {
		// gọi phương thức withdrawMoney, trừ tiền tài khoản của người dùng
		userService.withdrawMoney(currentUser.getUserId(), currentAccount.getPinHash(), amount);
		
		// sau khi rút, cập nhật thông tin tài khoản mới
	    Account updatedAccount = accountService.findAccountById(currentUser.getUserId());
	    
	    // cập nhật số dư mới
	    currentAccount.setBalance(updatedAccount.getBalance());
	    
	    // trả về currentAccount đã được cập nhật số dư mới
	    return currentAccount;
	}
}
