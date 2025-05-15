package group20.example.demo;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import group20.example.demo.entity.Transaction;
import group20.example.demo.entity.User;
import group20.example.demo.service.TransactionService;
import group20.example.demo.service.UserService;

@SpringBootApplication
public class AtmSimulationApplication implements CommandLineRunner {
	@Autowired
	TransactionService transactionService;

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		// SpringApplication.run(AtmSimulationApplication.class, args);
		SpringApplication.run(AtmSimulationApplication.class, args);
		// API a = new API();
		// a.getUSer();

	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		try {
			String email = "21130349@st.hcmuaf.edu.vn";
			String password = "21130349";
			String pin = "123456";
			String accountNumber = "1000000007";
			// Định dạng mong muốn
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

			// Lấy thời gian hiện tại và định dạng
			// LocalDateTime currentDateTime = LocalDateTime.now();
			// String formattedDateTime = currentDateTime.format(formatter);
			// Transaction transaction = new Transaction(accountNumber, "withdraw",
			// currentDateTime,
			// "withdraw 100.000 VND money");

			// transactionService.saveTransaction(transaction);
			System.out.println("Transaction saved successfully!");
			List<Transaction> transactions = transactionService
					.getAllTransactionsByAccountNumber(accountNumber, 10);
			transactions.forEach(t -> System.out.println("Description: " +
					t.getDescription()));
			boolean isExist = userService.existsByEmailAndPassword(email, password);
			if (isExist) {
				User user = userService.findByEmailAndPassword(email, password);
				Long userId = user.getUserId();
				// userService.withdrawMoney(userId, pin, 9999);
				userService.transferMoney(userId, pin, accountNumber, 1);

			} else {
				System.out.println("Account not found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: ======================" + e.getMessage());
		}
	}

}
