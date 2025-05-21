package group20.example.demo;


import javax.swing.SwingUtilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import group20.example.demo.view.LoginForm;

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


}
