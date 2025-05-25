package group20.example.demo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;

public class ATMErrorForm extends JFrame {

	private ApplicationContext context;

	public ATMErrorForm(ApplicationContext context) {
		this.context = context;
		initUI();
	}

	private void initUI() {
		setTitle("ATM - Out of Service");
		setLocationRelativeTo(null);
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(220, 220, 220));
		setContentPane(panel);

		JPanel jpTop = new JPanel(new BorderLayout());
		jpTop.setOpaque(false);
		jpTop.setBorder(new EmptyBorder(20, 30, 20, 30));

		JLabel labLogo = new JLabel("ATM Simulator");
		labLogo.setFont(new Font("Arial", Font.BOLD, 25));
		jpTop.add(labLogo, BorderLayout.WEST);

		JPanel jpHotline = new JPanel();
		jpHotline.setLayout(new BoxLayout(jpHotline, BoxLayout.Y_AXIS));
		jpHotline.setOpaque(false);

		JLabel labHot1 = new JLabel("HOTLINE ATM");
		labHot1.setFont(new Font("Arial", Font.PLAIN, 15));
		JLabel labHot2 = new JLabel("1900 1010 - 1010 1900");
		labHot2.setFont(new Font("Arial", Font.PLAIN, 15));
		jpHotline.add(labHot1);
		jpHotline.add(labHot2);

		jpTop.add(jpHotline, BorderLayout.EAST);
		panel.add(jpTop, BorderLayout.NORTH);

		// Content Panel
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setOpaque(false);

		JLabel englishMessage1 = new JLabel("This ATM is temporarily out of service");
		JLabel englishMessage2 = new JLabel("Sorry for any inconvenience caused");

		englishMessage1.setFont(new Font("Arial", Font.PLAIN, 25));
		englishMessage1.setAlignmentX(Component.CENTER_ALIGNMENT);
		englishMessage2.setFont(new Font("Arial", Font.PLAIN, 25));
		englishMessage2.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel vietnameseMessage1 = new JLabel("Máy ATM tạm ngừng phục vụ");
		JLabel vietnameseMessage2 = new JLabel("Chân thành xin lỗi quý khách");

		vietnameseMessage1.setFont(new Font("Arial", Font.PLAIN, 25));
		vietnameseMessage1.setAlignmentX(Component.CENTER_ALIGNMENT);
		vietnameseMessage2.setFont(new Font("Arial", Font.PLAIN, 25));
		vietnameseMessage2.setAlignmentX(Component.CENTER_ALIGNMENT);

		contentPanel.add(Box.createVerticalStrut(20));
		contentPanel.add(englishMessage1);
		contentPanel.add(englishMessage2);
		contentPanel.add(Box.createVerticalStrut(15));
		contentPanel.add(vietnameseMessage1);
		contentPanel.add(vietnameseMessage2);

		contentPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
		panel.add(contentPanel, BorderLayout.CENTER);
	}

	//public static void main(String[] args) {
	//	SwingUtilities.invokeLater(() -> {
	//		ATMErrorForm errorForm = new ATMErrorForm(null); // context null vì chỉ test UI
	//		errorForm.setVisible(true);
	//	});
	//}
}
