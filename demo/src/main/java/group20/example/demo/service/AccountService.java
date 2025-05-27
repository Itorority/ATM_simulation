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
	 * cập nhật số dư tài khoản
	 * 
	 * @param userId
	 * @param balance
	 * @return
	 */
	public void updateBalanceByUserId(Long userId, String pin, double balance) {
		// check the input balance from user is correct
		if (balance <= 0) {
			throw new IllegalArgumentException("Balance is invalid for transaction!!! ");
		}

		Account account = findAccountById(userId);
		// check account null
		if (account == null) {
			throw new IllegalArgumentException("Account not found ");
		}
		// check pin
		if (!account.getPinHash().equals(pin)) {
			throw new IllegalArgumentException("Invalid PIN for userId: " + account.getAccountNumber());
		}
		// check balance of the account is valid
		if (account.getBalance().compareTo(new BigDecimal(-1 * balance)) < 0) {
			throw new IllegalArgumentException("Invalid balance  ");
		}

		// check ATM amount valid ??
		if (!this.atmService.checkAmountATM(-1 * balance)) {
			throw new IllegalArgumentException("Invalid amount of ATM for doing transaction ");
		}
		if (balance < 0) {
			// save transaction
			Transaction transaction = new Transaction(account.getAccountNumber(), "WITHDRAW", LocalDateTime.now(),
					"Rút tiền " + balance * -1 + " VND");
			transactionService.saveTransaction(transaction);
			System.out.println("Transaction saved successfully!");
		}

		if (balance > 0) {
			// save transaction
			Transaction transaction = new Transaction(account.getAccountNumber(), "DEPOSIT", LocalDateTime.now(),
					"Nạp " + balance + ".000 VND vào tai khoản");
			transactionService.saveTransaction(transaction);
			System.out.println("Transaction saved successfully!");
		}

		// update user balance
		BigDecimal newBalance = account.getBalance().add(BigDecimal.valueOf(balance));
		System.out.println("New balance: " + newBalance);
		accountRepository.updateBalanceByUserId(userId, newBalance);

		// update ATM Amount
		this.atmService.updateAmount(balance);
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

