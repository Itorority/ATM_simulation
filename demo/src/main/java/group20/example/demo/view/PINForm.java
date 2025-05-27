package group20.example.demo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;

import group20.example.demo.controller.AccountController;
import group20.example.demo.controller.PINController;
import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;
import group20.example.demo.service.AccountService;

// PINForm là màn hình để hiển thị việc nhập mã PIN để xác thực các giao dịch như rút tiền, nạp tiền,...
public class PINForm extends JFrame implements IForm {

	private JTextField textPIN;
	private ApplicationContext context;
	private UserModel currentUser;
	private AccountModel currentAccount;
	private double amount;

	public PINForm(ApplicationContext context, UserModel currentUser, AccountModel currentAccount, double amount)
			throws HeadlessException {
		super();
		this.context = context;
		this.currentUser = currentUser;
		this.currentAccount = currentAccount;
		this.amount = amount;
		initUI();
	}

	public PINForm(ApplicationContext context, UserModel currentUser, AccountModel currentAccount) {
		this.context = context;
		this.currentUser = currentUser;
		this.currentAccount = currentAccount;
	}

	private void initUI() {
		setTitle("ATM - Nhập mã PIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(800, 600);
		setResizable(false);

		// Panel chính sử dụng BorderLayout
		JPanel jpMain = new JPanel(new BorderLayout());
		jpMain.setBackground(new Color(220, 220, 220));
		setContentPane(jpMain);

		// ====== Panel trên hiển thị Logo và Hotline =====
		JPanel jpTop = new JPanel();
		jpTop.setLayout(new BoxLayout(jpTop, BoxLayout.X_AXIS));
		jpTop.setOpaque(false);
		jpTop.setBorder(new EmptyBorder(20, 30, 20, 30));

		// Panel logo
		JLabel labLogo = new JLabel("ATM Simulator");
		labLogo.setFont(new Font("Arial", Font.BOLD, 25));
		jpTop.add(labLogo, BorderLayout.WEST);

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

		// ===== Panel trung tâm hiển thị nội dung và ô để nhập mã PIN =====
		JPanel jpCenter = new JPanel();
		jpCenter.setOpaque(false);
		jpCenter.setLayout(new BoxLayout(jpCenter, BoxLayout.Y_AXIS));
		jpCenter.setBorder(new EmptyBorder(20, 30, 20, 30));
		jpCenter.add(Box.createVerticalGlue());

		// Panel chứa nội dung
		JLabel labContent1 = new JLabel();
		labContent1.setText("Xin vui lòng nhập mã PIN để xác thực");
		labContent1.setFont(new Font("Arial", Font.BOLD, 38));
		labContent1.setAlignmentX(CENTER_ALIGNMENT);
		JLabel labContent2 = new JLabel();
		labContent2.setText("(Khách hàng vui lòng che tay khi nhập mã PIN)");
		labContent2.setFont(new Font("Arial", Font.PLAIN, 30));
		labContent2.setForeground(Color.RED);
		labContent2.setAlignmentX(CENTER_ALIGNMENT);

		// tạo text để nhập mã PIN
		textPIN = new JTextField(6);
		textPIN.setMaximumSize(new Dimension(450, 50));
		textPIN.setPreferredSize(new Dimension(450, 50));
		textPIN.setMinimumSize(new Dimension(450, 50));
		textPIN.setAlignmentX(CENTER_ALIGNMENT);
		textPIN.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));

		jpCenter.add(labContent1);
		jpCenter.add(Box.createVerticalStrut(20));
		jpCenter.add(labContent2);
		jpCenter.add(Box.createVerticalStrut(30));
		jpCenter.add(textPIN);

		jpCenter.add(Box.createVerticalGlue());

		// ===== Panel dưới để hiển thị các nút =====
		JPanel jpBottom = new JPanel();
		jpBottom.setOpaque(false);
		jpBottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jpBottom.setBorder(new EmptyBorder(20, 30, 20, 30));

		JPanel jpBtn = new JPanel();
		jpBtn.setOpaque(false);
		jpBtn.setLayout(new BoxLayout(jpBtn, BoxLayout.Y_AXIS));
		jpBtn.setAlignmentX(RIGHT_ALIGNMENT);

		// tạo 2 nút tiếp tục, huỷ bỉ
		JButton btnContinue = createButton("Tiếp tục");
		JButton btnCancel = createButton("Huỷ bỏ");

		// Gán sự kiện cho 2 nút
		btnContinue.addActionListener(e -> onButtonContinue());
		btnCancel.addActionListener(e -> onButtonCancel());

		jpBtn.add(btnContinue);
		jpBtn.add(Box.createRigidArea(new Dimension(0, 10)));
		jpBtn.add(btnCancel);

		jpBottom.add(jpBtn);

		jpMain.add(jpTop, BorderLayout.NORTH);
		jpMain.add(jpCenter, BorderLayout.CENTER);
		jpMain.add(jpBottom, BorderLayout.SOUTH);

	}

	// tạo nút với các định dạng cho sẵn
	private JButton createButton(String text) {
		Dimension btnSize = new Dimension(150, 45);
		JButton btn = new JButton(text);
		btn.setFont(new Font("Arial", Font.BOLD, 20));
		btn.setBackground(Color.BLUE);
		btn.setForeground(Color.WHITE);
		btn.setAlignmentX(CENTER_ALIGNMENT);
		btn.setMaximumSize(btnSize);
		btn.setPreferredSize(btnSize);
		btn.setMinimumSize(btnSize);
		return btn;
	}

	// tạo sự kiện cho nút tiếp tục
	private void onButtonContinue() {
		String inputPIN = textPIN.getText();
		PINController pinController = context.getBean(PINController.class);

		try {
			currentAccount = pinController.verifyPIN(currentUser, currentAccount, inputPIN, amount);

			JOptionPane.showMessageDialog(this, "Rút tiền thành công!");
			AccountService accountService = context.getBean(AccountService.class);
			AccountController accountController = new AccountController(accountService);
			AccountModel updatedAccount = accountController.findAccountById(currentUser.getUserId());
			MainForm mainForm = MainForm.getInstance(context, currentUser, updatedAccount);
			mainForm.setVisible(true);
			mainForm.setLocationRelativeTo(null);
			dispose();

		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}
	// tạo sự kiện cho nút huỷ bỏ
	private void onButtonCancel() {
		WithDrawForm backToWithDraw = new WithDrawForm(context, currentUser, currentAccount);
		backToWithDraw.setVisible(true);
		backToWithDraw.setLocationRelativeTo(null);
		dispose();
	}

	@Override
	public void showForm() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}

}
