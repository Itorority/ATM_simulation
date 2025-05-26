package group20.example.demo.view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;

public class MoneyTransferForm extends JFrame implements IForm {
	private final ApplicationContext context;
	private UserModel currentUser;
	private AccountModel currentAccount;

	public MoneyTransferForm(ApplicationContext context, UserModel currentUser, AccountModel currentAccount) {
		this.context = context;
		this.currentUser = currentUser;
		this.currentAccount = currentAccount;
		initUI();
		
	}

	private void initUI() {
		setTitle("ATM - Deposit");
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

		JLabel englishMessage = new JLabel("Money Tranfer");
		JLabel vietnameseMessage = new JLabel("Chuyển tiền");

		englishMessage.setFont(new Font("Arial", Font.PLAIN, 25));
		englishMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		vietnameseMessage.setFont(new Font("Arial", Font.PLAIN, 25));
		vietnameseMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel profileMessage = new JLabel("Người nhận");
		profileMessage.setFont(new Font("Arial", Font.PLAIN, 25));
		profileMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel profilePanel = new JPanel();
		profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
		profilePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		profilePanel.setBackground(Color.WHITE);

		JLabel userLabel = new JLabel("NGUYEN VAN A");
		userLabel.setFont(new Font("Arial", Font.BOLD, 20));
		userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel userIDLabel = new JLabel("User ID: 092122xx");
		userIDLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		userIDLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		profilePanel.add(Box.createVerticalStrut(10));
		profilePanel.add(Box.createVerticalStrut(10));
		profilePanel.add(userLabel);
		profilePanel.add(userIDLabel);
		profilePanel.add(Box.createVerticalStrut(10));

		JTextField moneyField = new JTextField();
		JLabel moneyText = new JLabel("Nhập số tiền cần chuyển");
		moneyText.setFont(new Font("Arial", Font.PLAIN, 25));
		moneyText.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel line = new JPanel();
		line.setPreferredSize(new Dimension(0, 1));
		line.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
		line.setBackground(Color.GRAY);

		JButton confirmButton = new JButton("Xác nhận");
		JButton cancelButton = new JButton("Hủy bỏ");

		Dimension buttonSize = new Dimension(200, 100);
		confirmButton.setPreferredSize(buttonSize);
		cancelButton.setPreferredSize(buttonSize);
		confirmButton.setMaximumSize(buttonSize);
		cancelButton.setMaximumSize(buttonSize);
		confirmButton.setFont(new Font("Arial", Font.BOLD, 18));
		cancelButton.setFont(new Font("Arial", Font.BOLD, 18));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(cancelButton);
		buttonPanel.add(Box.createHorizontalStrut(200));
		buttonPanel.add(confirmButton);
		buttonPanel.add(Box.createHorizontalGlue());

		contentPanel.add(Box.createVerticalStrut(20));
		contentPanel.add(englishMessage);
		contentPanel.add(vietnameseMessage);
		contentPanel.add(Box.createVerticalStrut(20));
		contentPanel.add(profileMessage);
		contentPanel.add(profilePanel);
		contentPanel.add(line);
		contentPanel.add(Box.createVerticalStrut(20));
		contentPanel.add(moneyText);
		contentPanel.add(moneyField);
		contentPanel.add(Box.createVerticalStrut(30));
		contentPanel.add(buttonPanel);

		contentPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
		panel.add(contentPanel, BorderLayout.CENTER);
	}

	@Override
	public void showForm() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> {
//			new MoneyTransferForm().setVisible(true);
//		});
//	}
}
