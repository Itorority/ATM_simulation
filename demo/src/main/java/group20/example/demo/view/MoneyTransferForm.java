package group20.example.demo.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;

import group20.example.demo.controller.MoneyTransferController;
import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;
import group20.example.demo.service.AccountService;

public class MoneyTransferForm extends JFrame implements IForm {
	private final ApplicationContext context;
	private final UserModel currentUser;
	private final AccountModel currentAccount;

	public MoneyTransferForm(ApplicationContext context, UserModel currentUser, AccountModel currentAccount) {
		this.context = context;
		this.currentUser = currentUser;
		this.currentAccount = currentAccount;
		initUI();
	}

	private void initUI() {
		setTitle("ATM - Money Transfer");
		setLocationRelativeTo(null); 
		setSize(800, 600); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false); 

		// Panel chính sử dụng BorderLayout
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(new Color(220, 220, 220));
		setContentPane(mainPanel);

		// ===== Panel trên cùng: chứa logo và hotline =====
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

		mainPanel.add(jpTop, BorderLayout.NORTH); 

		// ===== Panel nội dung chính =====
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setOpaque(false);


		JLabel titleVN = new JLabel("Chuyển tiền");
		titleVN.setFont(new Font("Arial", Font.BOLD, 38));
		titleVN.setAlignmentX(Component.CENTER_ALIGNMENT);

		contentPanel.add(titleVN);
		contentPanel.add(Box.createVerticalStrut(20)); 

		// Các ô nhập liệu
		JTextField accountNumberField = new JTextField(); // số tài khoản người nhận
		JTextField pinField = new JPasswordField(); // mã PIN người gửi
		JTextField moneyField = new JTextField(); // số tiền cần chuyển

		JLabel recipientLabel = new JLabel("Nhập số tài khoản người nhận:");
		JLabel pinLabel = new JLabel("Nhập mã PIN:");
		JLabel amountLabel = new JLabel("Nhập số tiền cần chuyển:");

		Font labelFont = new Font("Arial", Font.PLAIN, 20);
		recipientLabel.setFont(labelFont);
		pinLabel.setFont(labelFont);
		amountLabel.setFont(labelFont);

		recipientLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		pinLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		amountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		accountNumberField.setAlignmentX(Component.CENTER_ALIGNMENT);
		pinField.setAlignmentX(Component.CENTER_ALIGNMENT);
		moneyField.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Kích thước của ô input
		int inputWidth = 400;
		int inputHeight = 40;

		accountNumberField.setMaximumSize(new Dimension(inputWidth, inputHeight));
		pinField.setMaximumSize(new Dimension(inputWidth, inputHeight));
		moneyField.setMaximumSize(new Dimension(inputWidth, inputHeight));

		Font inputFont = new Font("Arial", Font.PLAIN, 20);
		accountNumberField.setFont(inputFont);
		pinField.setFont(inputFont);
		moneyField.setFont(inputFont);

		contentPanel.add(Box.createVerticalStrut(10));
		contentPanel.add(recipientLabel);
		contentPanel.add(accountNumberField);
		contentPanel.add(Box.createVerticalStrut(10));
		contentPanel.add(pinLabel);
		contentPanel.add(pinField);
		contentPanel.add(Box.createVerticalStrut(10));
		contentPanel.add(amountLabel);
		contentPanel.add(moneyField);
		contentPanel.add(Box.createVerticalStrut(30));

		// ===== Các nút xác nhận và hủy =====
		JButton confirmButton = new JButton("Xác nhận");
		JButton cancelButton = new JButton("Hủy bỏ");

		confirmButton.setFont(new Font("Arial", Font.BOLD, 25));
		cancelButton.setFont(new Font("Arial", Font.BOLD, 25));
		
		confirmButton.setBackground(Color.BLUE);
		confirmButton.setForeground(Color.WHITE);

		cancelButton.setBackground(Color.BLUE);
		cancelButton.setForeground(Color.WHITE);

		// Panel chứa nút
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
		contentPanel.setBorder(new EmptyBorder(30, 100, 50, 100));

		mainPanel.add(contentPanel, BorderLayout.CENTER); 

		// ===== Xử lý sự kiện nút =====
		AccountService accountService = context.getBean(AccountService.class);
		MoneyTransferController controller = new MoneyTransferController(accountService, currentAccount);

		// Sự kiện khi bấm nút "Xác nhận"
		confirmButton.addActionListener(e -> {
			try {
				String recipientAccount = accountNumberField.getText().trim(); // tài khoản nhận
				String pin = pinField.getText().trim(); // mã PIN
				double amount = Double.parseDouble(moneyField.getText().trim()); // số tiền chuyển

				controller.transfer(currentUser.getUserId(), pin, recipientAccount, amount); // gọi controller xử lý

				JOptionPane.showMessageDialog(this, "Chuyển tiền thành công!");

				// Quay lại màn hình chính
				MainForm mainForm = MainForm.getInstance(context, currentUser, currentAccount);
				mainForm.setVisible(true);
				mainForm.setLocationRelativeTo(null);
				dispose();

			} catch (Exception ex) {
				// Hiển thị thông báo lỗi nếu có ngoại lệ
				JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Chuyển tiền thất bại", JOptionPane.ERROR_MESSAGE);
			}
		});

		// Sự kiện khi bấm nút "Hủy bỏ"
		cancelButton.addActionListener(e -> {
			MainForm mainForm = MainForm.getInstance(context, currentUser, currentAccount);
			mainForm.setVisible(true);
			mainForm.setLocationRelativeTo(null);
			dispose();
		});
	}

	@Override
	public void showForm() {
		this.setVisible(true);
	}
}
