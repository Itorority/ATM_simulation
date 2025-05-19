package group20.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import javax.swing.SwingUtilities;

@SpringBootApplication
public class AtmSimulationApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(AtmSimulationApplication.class);
		System.out.println("Frame chính sẽ bắt đầu từ line tiếp theo");

		SwingUtilities.invokeLater(() -> {
            	LoginForm loginForm = new LoginForm(context);
            	loginForm.setVisible(true);
        	});
	}

	// @Override
	// public void run(String... args) throws Exception {
	// // TODO Auto-generated method stub
	// try {
	// String email = "21130349@st.hcmuaf.edu.vn";
	// String password = "21130349";
	// String pin = "123456";
	// String accountNumber = "1000000007";
	// // Định dạng mong muốn
	// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
	// HH:mm:ss.SSS");

	// // Lấy thời gian hiện tại và định dạng
	// // LocalDateTime currentDateTime = LocalDateTime.now();
	// // String formattedDateTime = currentDateTime.format(formatter);
	// // Transaction transaction = new Transaction(accountNumber, "withdraw",
	// // currentDateTime,
	// // "withdraw 100.000 VND money");
	// // transactionService.saveTransaction(transaction);
	// System.out.println("Transaction saved successfully!");
	// List<Transaction> transactions = transactionService
	// .getAllTransactionsByAccountNumber(accountNumber, 10);
	// transactions.forEach(t -> System.out.println("Description: " +
	// t.getDescription()));
	// boolean isExist = userService.existsByEmailAndPassword(email, password);
	// if (isExist) {
	// User user = userService.findByEmailAndPassword(email, password);
	// Long userId = user.getUserId();
	// // userService.withdrawMoney(userId, pin, 9999);
	// userService.transferMoney(userId, pin, accountNumber, 1);

	// } else {
	// System.out.println("Account not found");
	// }
	// } catch (Exception e) {
	// // TODO: handle exception
	// System.out.println("Error: ======================" + e.getMessage());
	// }
	// }

}
