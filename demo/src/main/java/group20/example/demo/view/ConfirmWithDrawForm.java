package group20.example.demo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;

import group20.example.demo.controller.AccountController;
import group20.example.demo.controller.UserController;
import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;

public class ConfirmWithDrawForm extends JFrame {

	private ApplicationContext context;
	private UserModel currentUser;
	private AccountModel currentAccount;
	private double amount;

	public ConfirmWithDrawForm(ApplicationContext context, UserModel currentUser, AccountModel currentAccount,
			double amount) {
		this.context = context;
		this.currentUser = currentUser;
		this.currentAccount = currentAccount;
		this.amount = amount;
		initUI();

	}

	private void initUI() {
		setTitle("ATM - Xác nhận rút tiền");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		// main
		JPanel jpMain = new JPanel(new BorderLayout());
		jpMain.setBackground(new Color(220, 220, 220));
		setContentPane(jpMain);

		// top panel
		JPanel jpTop = new JPanel();
		jpTop.setLayout(new BoxLayout(jpTop, BoxLayout.X_AXIS));
		jpTop.setBorder(new EmptyBorder(20, 30, 20, 30));

		JLabel labLogo = new JLabel("ATM Simulator");
		labLogo.setFont(new Font("Arial", Font.BOLD, 25));
		jpTop.add(labLogo);

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

		// center panel
		JPanel jpCenter = new JPanel();
		jpCenter.setLayout(new BoxLayout(jpCenter, BoxLayout.Y_AXIS));
		jpCenter.setBorder(new EmptyBorder(100, 30, 20, 30));

		JLabel labContent = new JLabel();
		labContent.setText("Bạn có chắc chắn muốn rút số tiền");
		labContent.setFont(new Font("Arial", Font.BOLD, 38));
		labContent.setAlignmentX(CENTER_ALIGNMENT);

		JLabel labBalance = new JLabel(String.format("%,.0f VNĐ", amount));
		labBalance.setFont(new Font("Arial", Font.BOLD, 50));
		labBalance.setAlignmentX(CENTER_ALIGNMENT);

		jpCenter.add(labContent);
		jpCenter.add(Box.createVerticalStrut(20));
		jpCenter.add(labBalance);

		// bottom panel
		JPanel jpBottom = new JPanel();
		jpBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		jpBottom.setBorder(new EmptyBorder(20, 30, 100, 30));

		JButton btnConfirm = createButton("Xác nhận");
		JButton btnCancel = createButton("Huỷ bỏ");
		
		btnConfirm.addActionListener(e -> onButtonConfirm());
		btnCancel.addActionListener(e -> onButtonCancel());

		jpBottom.add(btnConfirm);
		jpBottom.add(Box.createHorizontalStrut(30));
		jpBottom.add(btnCancel);
		
		jpMain.add(jpTop, BorderLayout.NORTH);
		jpMain.add(jpCenter, BorderLayout.CENTER);
		jpMain.add(jpBottom, BorderLayout.SOUTH);
	}

	private JButton createButton(String text) {
		Dimension btnSize = new Dimension(200, 60);
		JButton btn = new JButton(text);
		btn.setFont(new Font("Arial", Font.PLAIN, 20));
		btn.setBackground(Color.BLUE);
		btn.setForeground(Color.WHITE);
		btn.setAlignmentX(CENTER_ALIGNMENT);
		btn.setMaximumSize(btnSize);
		btn.setPreferredSize(btnSize);
		btn.setMinimumSize(btnSize);
		return btn;
	}

	private void onButtonConfirm() {
		UserController userController = context.getBean(UserController.class);
		AccountController accountController = context.getBean(AccountController.class);
		
		try {
			userController.withdrawMoney(currentUser.getUserId(), currentAccount.getPinHash(), amount);
			
			currentAccount = accountController.findAccountById(currentUser.getUserId());
			
			JOptionPane.showMessageDialog(this, "Rút tiền thành công!");
			
		        MainForm mainForm = MainForm.getInstance(context, currentUser, currentAccount);
		        mainForm.setLocationRelativeTo(null);
		        mainForm.setVisible(true);
		        dispose();

		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void onButtonCancel() {
		WithDrawForm backTowithDrawForm = new WithDrawForm(context, currentUser, currentAccount);
		backTowithDrawForm.setVisible(true);
		backTowithDrawForm.setLocationRelativeTo(null);
		dispose();

	}
}

