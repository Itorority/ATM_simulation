package group20.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group20.example.demo.entity.Account;
import group20.example.demo.entity.Transaction;
import group20.example.demo.repo.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private ATMService atmService;

	@Autowired
	public TransactionService transactionService;

	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	/**
	 * kiểm tra tài khoản có tồn tại hay không
	 * 
	 * @param userId
	 * @return true nếu tài khoản tồn tại, false nếu không
	 */
	public Boolean isAccountExist(Long userId) {
		return accountRepository.existsByUserId(userId);
	}

	/**
	 * lấy thông tin tài khoản dựa vào id người dùng object
	 * 
	 * @param userId
	 * @return Account
	 */
	public Account findAccountById(Long userId) {
		return accountRepository.findByUserId(userId);
	}

	/**
	 * Nạp tiền vào tài khoản
	 *
	 * @param userId
	 * @param pin
	 * @param amount
	 */
	public void depositMoney(Long userId, String pin, double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Số tiền nạp phải lớn hơn 0.");
		}

		Account account = findAccountById(userId);
		if (account == null) {
			throw new IllegalArgumentException("Không tìm thấy tài khoản.");
		}

		if (!account.getPinHash().equals(pin)) {
			throw new IllegalArgumentException("Mã PIN không hợp lệ cho tài khoản: " + account.getAccountNumber());
		}

		// Lưu transaction
		Transaction transaction = new Transaction(account.getAccountNumber(), "DEPOSIT", LocalDateTime.now(),
				"Nạp " + amount + ".000 VND vào tài khoản");
		transactionService.saveTransaction(transaction);
		System.out.println("Transaction saved successfully!");

		// Cập nhật số dư tài khoản
		BigDecimal newBalance = account.getBalance().add(BigDecimal.valueOf(amount));
		System.out.println("New balance: " + newBalance);
		accountRepository.updateBalanceByUserId(userId, newBalance);

		// Cập nhật số tiền trong ATM
		this.atmService.updateAmount(amount);
	}

	/**
	 * Rút tiền từ tài khoản
	 *
	 * @param userId
	 * @param pin
	 * @param amount
	 */
	public void withdrawMoney(Long userId, String pin, double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Số tiền rút phải lớn hơn 0.");
		}

		Account account = findAccountById(userId);
		if (account == null) {
			throw new IllegalArgumentException("Không tìm thấy tài khoản.");
		}

		if (!account.getPinHash().equals(pin)) {
			throw new IllegalArgumentException("Mã PIN không hợp lệ cho tài khoản: " + account.getAccountNumber());
		}

		if (account.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0) {
			throw new IllegalArgumentException("Số dư tài khoản không đủ.");
		}

		if (!this.atmService.checkAmountATM(amount)) {
			throw new IllegalArgumentException("Số tiền trong ATM không đủ để rút.");
		}

		// Lưu transaction
		Transaction transaction = new Transaction(account.getAccountNumber(), "WITHDRAW", LocalDateTime.now(),
				"Rút tiền " + amount + " VND");
		transactionService.saveTransaction(transaction);
		System.out.println("Transaction saved successfully!");

		// Cập nhật số dư tài khoản
		BigDecimal newBalance = account.getBalance().subtract(BigDecimal.valueOf(amount));
		System.out.println("New balance: " + newBalance);
		accountRepository.updateBalanceByUserId(userId, newBalance);

		// Cập nhật số tiền trong ATM
		this.atmService.updateAmount(-amount);
	}

	/**
	 * Transfer money from account by using numberaccount and muiltiply by -1
	 * 
	 * @param userId        the user id that transfer money to another account
	 * @param pin           the pin of user
	 * @param accountNumber the account number that userId want to transfer money
	 * @param money
	 */
	public void updateBalanceByAccountNumber(long userId, String pin, String accountNumber, double money) {
		// check the input balance from user is correct
		if (money <= 0) {
			throw new IllegalArgumentException("Balance is invalid for transaction!!! ");
		}
		// check pin correct
		Account sender = findAccountById(userId);
		if (!sender.getPinHash().equals(pin)) {
			throw new IllegalArgumentException("Invalid PIN for userId: " + sender.getAccountNumber());
		}

		// check balance of user want to transfer
		if (sender.getBalance().compareTo(new BigDecimal(money)) < 0) {
			throw new IllegalArgumentException("Invalid balance ");
		}
		// check account is exist
		Account reciver = accountRepository.findByAccountNumber(accountNumber);
		// check account null
		if (reciver == null) {
			throw new IllegalArgumentException("Account not found for account number: " + accountNumber);

		}

		// update số tiền của sender
		BigDecimal senderBalance = sender.getBalance().subtract(BigDecimal.valueOf(money));
		accountRepository.updateBalanceByUserId(sender.getUserId(), senderBalance);
		// save transaction of sender
		Transaction senderTransaction = new Transaction(sender.getAccountNumber(), "TRANSFER", LocalDateTime.now(),
				"Chuyển số tiền : " + money + ".000 VND vào tai khoản " + reciver.getAccountNumber() + " .");
		transactionService.saveTransaction(senderTransaction);
		System.out.println("Transaction saved successfully!");

		// lấy số tiền hiện tại của reciver
		BigDecimal reciverBalance = reciver.getBalance().add(BigDecimal.valueOf(money));
		// cập nhật lại vào tài khoản của người gửi
		accountRepository.updateBalanceByUserId(reciver.getUserId(), reciverBalance);

		// save transaction of reciver
		Transaction reciverTransaction = new Transaction(reciver.getAccountNumber(), "TRANSFER", LocalDateTime.now(),
				"Nhận số tiền : " + money + ".000 VND từ tài khoản " + sender.getAccountNumber() + " .");
		transactionService.saveTransaction(reciverTransaction);
		System.out.println("Transaction saved successfully!");

	}

	/**
	 * Thay đổi mã PIN
	 * 
	 * @param userId
	 * @param newPIN
	 * @return
	 */

	public void changePIN(Long userId, String newPIN) {
		Account account = findAccountById(userId);
		account.setPinHash(newPIN);
		accountRepository.updatePinByUserId(userId, newPIN);
	}
}
