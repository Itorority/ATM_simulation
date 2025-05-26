package group20.example.demo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.math.BigDecimal;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;

import group20.example.demo.controller.MainController;
import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;

public class MainForm extends JFrame implements IForm {

    private static MainForm instance;
    private final ApplicationContext context;

    private UserModel currentUser;
    private AccountModel currentAccount;

    public MainForm(ApplicationContext context, UserModel user, AccountModel account) {
        this.context = context;
        this.currentUser = user;
        this.currentAccount = account;
        initUI();
    }

    public static MainForm getInstance(ApplicationContext context, UserModel user, AccountModel account) {
        if (instance == null) {
            instance = new MainForm(context, user, account);
        }
        return instance;
    }

    public ApplicationContext getApplicationContext() {
        return context;
    }

    private void initUI() {
        setTitle("ATM - Màn hình chính");
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

        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));
        logoPanel.setOpaque(false);

        JLabel labLogo = new JLabel("ATM Simulator");
        labLogo.setFont(new Font("Arial", Font.BOLD, 25));

        String usernameText = "Người dùng: " + (currentUser != null ? currentUser.getFullName() : "Chưa đăng nhập");
        JLabel usernameLabel = new JLabel(usernameText);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameLabel.setForeground(Color.DARK_GRAY);

        String balanceText = "Số dư: " + (currentAccount != null ? String.format("%,.0f VNĐ", currentAccount.getBalance()) : "0 VNĐ");
        JLabel balanceLabel = new JLabel(balanceText);
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        balanceLabel.setForeground(Color.DARK_GRAY);

        logoPanel.add(labLogo);
        logoPanel.add(usernameLabel);
        logoPanel.add(balanceLabel);

        JButton profileButton = new JButton("Hồ sơ");
        profileButton.setFont(new Font("Arial", Font.BOLD, 18));
        profileButton.setBackground(new Color(30, 144, 255));
        profileButton.setForeground(Color.WHITE);
       
        JPanel profilePanel = new JPanel();
        profilePanel.setOpaque(false);
        profilePanel.add(profileButton);

        jpTop.add(profilePanel, BorderLayout.CENTER); // hoặc EAST nếu muốn
        logoPanel.add(profileButton);
        jpTop.add(logoPanel, BorderLayout.WEST);

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

        jpTop.add(jpHotline, BorderLayout.EAST);
        panel.add(jpTop, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(null);
        contentPanel.setOpaque(false);
        panel.add(contentPanel, BorderLayout.CENTER);

        JLabel welcomeLabel = new JLabel("Chào mừng đến với hệ thống ATM");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setBounds(155, 60, 500, 40);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(welcomeLabel);

        JLabel instructionLabel = new JLabel("Vui lòng lựa chọn giao dịch:");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 23));
        instructionLabel.setForeground(Color.RED);
        instructionLabel.setBounds(270, 120, 300, 30);
        contentPanel.add(instructionLabel);

        JButton rutTienBtn = new JButton("Rút tiền");
        rutTienBtn.setBounds(150, 200, 200, 50);
        JButton chuyenKhoanBtn = new JButton("Chuyển khoản");
        chuyenKhoanBtn.setBounds(450, 200, 200, 50);
        JButton napTienBtn = new JButton("Nạp tiền");
        napTienBtn.setBounds(150, 280, 200, 50);
        JButton doiPinBtn = new JButton("Đổi PIN");
        doiPinBtn.setBounds(450, 280, 200, 50);

        for (JButton btn : new JButton[] { rutTienBtn, chuyenKhoanBtn, napTienBtn, doiPinBtn }) {
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setForeground(Color.WHITE);
            btn.setBackground(Color.BLUE);
            contentPanel.add(btn);
        }

        MainController controller = new MainController(context, currentUser, currentAccount);

        rutTienBtn.addActionListener(e -> controller.openWithdrawForm(this));
        napTienBtn.addActionListener(e -> controller.openDepositForm(this));
        chuyenKhoanBtn.addActionListener(e -> controller.openTransferForm(this));
        doiPinBtn.addActionListener(e -> controller.openPinForm(this));
        profileButton.addActionListener(e -> controller.openProfileForm(this));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserModel mockUser = new UserModel();
            mockUser.setFullName("Nguyễn Văn A");
            //mockUser.setUserId("user123");
            mockUser.setEmail("nguyenvana@email.com");
            mockUser.setPhoneNumber("0909123456");

            AccountModel mockAccount = new AccountModel();
            mockAccount.setBalance(new BigDecimal("10000000")); // 10 triệu
            mockAccount.setPinHash("1234");

            MainForm mainForm = new MainForm(null, mockUser, mockAccount);
            mainForm.setVisible(true);
        });
    }

	@Override
	public void showForm() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}
}
