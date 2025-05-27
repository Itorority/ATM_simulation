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

		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(220, 220, 220));
        setContentPane(mainPanel);

        // ===== Panel phía trên chứa logo và hotline =====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel labLogo = new JLabel("ATM Simulator");
        labLogo.setFont(new Font("Arial", Font.BOLD, 25));
        topPanel.add(labLogo);

        topPanel.add(Box.createHorizontalGlue()); // Hotline được đính sang bên phải

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
        topPanel.add(jpHotline);

        mainPanel.add(topPanel, BorderLayout.NORTH);

		// Content
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setOpaque(false);

		// Title
		JLabel titleEN = new JLabel("Money Transfer");
		titleEN.setFont(new Font("Arial", Font.BOLD, 25));
		titleEN.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(titleEN);

		JLabel titleVN = new JLabel("Chuyển tiền");
		titleVN.setFont(new Font("Arial", Font.PLAIN, 25));
		titleVN.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(titleVN);

		contentPanel.add(Box.createVerticalStrut(20));

		// Input fields
		JTextField accountNumberField = new JTextField();
		JTextField pinField = new JPasswordField();
		JTextField moneyField = new JTextField();

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
		
		int inputWidth = 400;
		int inputHeight = 40;

		accountNumberField.setMaximumSize(new Dimension(inputWidth, inputHeight));
		pinField.setMaximumSize(new Dimension(inputWidth, inputHeight));
		moneyField.setMaximumSize(new Dimension(inputWidth, inputHeight));

		// Set font bigger for better visibility
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

		// Buttons
		JButton confirmButton = new JButton("Xác nhận");
		JButton cancelButton = new JButton("Hủy bỏ");

		confirmButton.setFont(new Font("Arial", Font.BOLD, 18));
		cancelButton.setFont(new Font("Arial", Font.BOLD, 18));
		
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

		contentPanel.add(buttonPanel);
		contentPanel.setBorder(new EmptyBorder(30, 100, 50, 100));
		mainPanel.add(contentPanel, BorderLayout.CENTER);

		// Button Actions
		AccountService accountService = context.getBean(AccountService.class);
		MoneyTransferController controller = new MoneyTransferController(accountService, currentAccount);

		confirmButton.addActionListener(e -> {
			
			try {
				String recipientAccount = accountNumberField.getText().trim();
				String pin = pinField.getText().trim();
				double amount = Double.parseDouble(moneyField.getText().trim());

				controller.transfer(currentUser.getUserId(), pin, recipientAccount, amount);
				
				JOptionPane.showMessageDialog(this, "Chuyển tiền thành công!");
				
				MainForm mainForm = MainForm.getInstance(context, currentUser, currentAccount);
				mainForm.setVisible(true);
				mainForm.setLocationRelativeTo(null);
				dispose();
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Chuyển tiền thất bại", JOptionPane.ERROR_MESSAGE);
			}
			
		});

		cancelButton.addActionListener(e -> {
			MainForm mainForm = MainForm.getInstance(context, currentUser, currentAccount);
			mainForm.setVisible(true);
			mainForm.setLocationRelativeTo(null);
			dispose();
		});
	}
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Dummy ApplicationContext (null since we don't use it here)
            ApplicationContext dummyContext = null;

            // Mock current user
            UserModel mockUser = new UserModel();
            mockUser.setUserId(1L);
            mockUser.setUserId(1l);

            // Mock current account
            AccountModel mockAccount = new AccountModel();
            mockAccount.setAccountNumber("1234567890");
            mockAccount.setBalance(java.math.BigDecimal.valueOf(1000000)); // 1 million VND

            // Create and show the form
            MoneyTransferForm form = new MoneyTransferForm(dummyContext, mockUser, mockAccount);
            form.showForm();
        });
    }

	@Override
	public void showForm() {
		this.setVisible(true);
	}
}
