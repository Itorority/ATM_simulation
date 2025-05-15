package group20.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import group20.example.demo.entity.User;
import group20.example.demo.service.UserService;

@SpringBootApplication
public class AtmSimulationApplication implements CommandLineRunner {
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

			boolean isExist = userService.existsByEmailAndPassword(email, password);
			if (isExist) {
				User user = userService.findByEmailAndPassword(email, password);
				Long userId = user.getUserId();
				// userService.withdrawMoney(userId, pin, 9999);
				userService.transferMoney(userId, pin, accountNumber, 999);

				System.out.println("depositMoney successful");
			} else {
				System.out.println("Account not found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: ======================" + e.getMessage());
		}
	}

}
