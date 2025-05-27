package group20.example.demo.view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

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


import group20.example.demo.controller.UserController;
import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;

public class DepositForm extends JFrame implements IForm {
	private JTextField moneyField;
	private final ApplicationContext context;
	private UserModel currentUser;
	private AccountModel currentAccount;
	private UserController controller;
	

	public DepositForm(ApplicationContext context, UserModel currentUser, AccountModel currentAccount) {
		this.controller = context.getBean(UserController.class);
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

		JPanel jpTop = new JPanel();
		jpTop.setLayout(new BoxLayout(jpTop, BoxLayout.X_AXIS));
		jpTop.setOpaque(false);
		jpTop.setBorder(new EmptyBorder(20, 30, 20, 30));
		
		// Panel logo
		JLabel labLogo = new JLabel("ATM Simulator");
		labLogo.setFont(new Font("Arial", Font.BOLD, 25));
		jpTop.add(labLogo);

		// Panel Hotline
		JPanel jpHotline = new JPanel();
		jpHotline.setLayout(new BoxLayout(jpHotline, BoxLayout.Y_AXIS));
		jpHotline.setOpaque(false);
		JLabel labHot1 = new JLabel("HOTLINE ATM");
		labHot1.setFont(new Font("Arial", Font.PLAIN, 15));
		labHot1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		JLabel labHot2 = new JLabel("1900 1010 - 1010 1900");
		labHot2.setFont(new Font("Arial", Font.PLAIN, 15));
		labHot2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		jpHotline.add(labHot1);
		jpHotline.add(labHot2);
		jpTop.add(Box.createHorizontalGlue());
		jpTop.add(jpHotline, BorderLayout.EAST);
		
		panel.add(jpTop, BorderLayout.NORTH);

		// Content Panel
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setOpaque(false);
		
		JLabel titleVN = new JLabel("Nạp tiền");
		titleVN.setFont(new Font("Arial", Font.BOLD, 38));
		titleVN.setAlignmentX(Component.CENTER_ALIGNMENT);

		contentPanel.add(titleVN);
		contentPanel.add(Box.createVerticalStrut(20)); 

		JLabel vietnameseMessage = new JLabel("Quý khách vui lòng đưa tiền vào khay và xác nhận nạp tiền");

		vietnameseMessage.setFont(new Font("Arial", Font.PLAIN, 25));
		vietnameseMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

		moneyField = new JTextField();
		moneyField.setMaximumSize(new Dimension(400, 40)); 
		moneyField.setPreferredSize(new Dimension(400, 40)); 
		JLabel moneyText = new JLabel("Số tiền đã để vào khay");
		moneyText.setFont(new Font("Arial", Font.PLAIN, 25));
		moneyText.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton confirmButton = new JButton("Xác nhận");
		JButton cancelButton = new JButton("Hủy bỏ");

		confirmButton.setFont(new Font("Arial", Font.BOLD, 25));
		cancelButton.setFont(new Font("Arial", Font.BOLD, 25));
		
		confirmButton.setBackground(Color.BLUE);
		confirmButton.setForeground(Color.WHITE);

		cancelButton.setBackground(Color.BLUE);
		cancelButton.setForeground(Color.WHITE);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(cancelButton);
		buttonPanel.add(Box.createHorizontalStrut(50));
		buttonPanel.add(confirmButton);
		buttonPanel.add(Box.createHorizontalGlue());

		// Thêm button panel vào content panel
		contentPanel.add(buttonPanel);
		contentPanel.setBorder(new EmptyBorder(30, 100, 20, 100));

		contentPanel.add(vietnameseMessage);
		contentPanel.add(Box.createVerticalStrut(30));
		contentPanel.add(moneyText);
		contentPanel.add(moneyField);
		contentPanel.add(Box.createVerticalStrut(40));
		contentPanel.add(buttonPanel);

		contentPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
		panel.add(contentPanel, BorderLayout.CENTER);
		confirmButton.addActionListener(e -> {
			try {
				String moneyTextValue = moneyField.getText().trim();
				if (moneyTextValue.isEmpty()) {
					javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền!");
					return;
				}

				double money = Double.parseDouble(moneyTextValue);
				if (money <= 0) {
					javax.swing.JOptionPane.showMessageDialog(this, "Số tiền phải lớn hơn 0!");
					return;
				}
				
				// Mở form nhập PIN cho nạp tiền
				DepositPINForm pinForm = new DepositPINForm(context, currentUser, currentAccount, money);
				pinForm.setVisible(true);
				pinForm.setLocationRelativeTo(null);
				dispose();
				
			} catch (NumberFormatException ex) {
				javax.swing.JOptionPane.showMessageDialog(this, "Số tiền không hợp lệ!");
			} catch (Exception ex) {
				javax.swing.JOptionPane.showMessageDialog(this, "Có lỗi: " + ex.getMessage());
			}
		});

		cancelButton.addActionListener(e -> {
			MainForm mainForm = MainForm.getInstance(context, currentUser, currentAccount);
			mainForm.setLocationRelativeTo(null);
			mainForm.setVisible(true);
			dispose();
		});

	}

	public void depositMoney(Long id, String pin, double money) {
		this.controller.depositMoney(id, pin, money);

	}
	

	@Override
	public void showForm() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> {
//			new DepositForm().setVisible(true);
//		});
//	}
}
