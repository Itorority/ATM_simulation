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
import group20.example.demo.model.ChangeListener;
import group20.example.demo.model.UserModel;

/**
 * MainForm là cửa sổ chính sau khi người dùng đăng nhập thành công.
 * Hiển thị thông tin người dùng, số dư tài khoản và các chức năng chính của ATM.
 */
public class MainForm extends JFrame implements IForm, ChangeListener {

    private static MainForm instance;
    private final ApplicationContext context;

    private UserModel currentUser;
    private AccountModel currentAccount;

    private JLabel balanceLabel;

    public MainForm(ApplicationContext context, UserModel user, AccountModel account) {
        this.context = context;
        this.currentUser = user;
        this.currentAccount = account;

        // Lắng nghe thay đổi số dư tài khoản
        if (currentAccount != null) {
            currentAccount.addBalanceChangeListener(this);
        }

        initUI();
    }

    /**
     * Singleton pattern để chỉ tạo một instance duy nhất của MainForm.
     */
    public static MainForm getInstance(ApplicationContext context, UserModel user, AccountModel account) {
        if (instance == null) {
            instance = new MainForm(context, user, account);
        }
        return instance;
    }

    /**
     * Reset lại instance hiện tại (để đăng xuất hoặc tạo form mới).
     */
    public static void resetInstance() {
        if (instance != null) {
            instance.dispose(); // Đóng cửa sổ hiện tại
            instance = null;    // Xoá instance
        }
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

        // Panel chính
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(220, 220, 220));
        setContentPane(panel);

        // Panel trên cùng hiển thị thông tin người dùng và hotline
        JPanel jpTop = new JPanel(new BorderLayout());
        jpTop.setOpaque(false);
        jpTop.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Panel logo + user info
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
        balanceLabel = new JLabel(balanceText);
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        balanceLabel.setForeground(Color.DARK_GRAY);

        logoPanel.add(labLogo);
        logoPanel.add(usernameLabel);
        logoPanel.add(balanceLabel);

        // Nút truy cập hồ sơ người dùng
        JButton profileButton = new JButton("Hồ sơ");
        profileButton.setFont(new Font("Arial", Font.BOLD, 18));
        profileButton.setBackground(new Color(30, 144, 255));
        profileButton.setForeground(Color.WHITE);

        // Panel chứa nút hồ sơ
        JPanel profilePanel = new JPanel();
        profilePanel.setOpaque(false);
        profilePanel.add(profileButton);

        // Gắn các thành phần lên panel trên
        jpTop.add(profilePanel, BorderLayout.CENTER);
        logoPanel.add(profileButton);
        jpTop.add(logoPanel, BorderLayout.WEST);

        // Panel hotline phía bên phải
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

        // Panel nội dung chính
        JPanel contentPanel = new JPanel(null);
        contentPanel.setOpaque(false);
        panel.add(contentPanel, BorderLayout.CENTER);

        // Tiêu đề chào mừng
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

        // Các nút chức năng giao dịch
        JButton rutTienBtn = new JButton("Rút tiền");
        rutTienBtn.setBounds(150, 170, 200, 50);

        JButton chuyenKhoanBtn = new JButton("Chuyển khoản");
        chuyenKhoanBtn.setBounds(450, 170, 200, 50);

        JButton napTienBtn = new JButton("Nạp tiền");
        napTienBtn.setBounds(150, 250, 200, 50);

        JButton doiPinBtn = new JButton("Đổi PIN");
        doiPinBtn.setBounds(450, 250, 200, 50);

        JButton lichSuGiaoDichBtn = new JButton("Lịch sử giao dịch");
        lichSuGiaoDichBtn.setBounds(150, 330, 200, 50);

        // Cài đặt style cho các nút
        for (JButton btn : new JButton[] { rutTienBtn, chuyenKhoanBtn, napTienBtn, doiPinBtn, lichSuGiaoDichBtn }) {
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setForeground(Color.WHITE);
            btn.setBackground(Color.BLUE);
            contentPanel.add(btn);
        }

        // Tạo controller để điều hướng các chức năng
        MainController controller = new MainController(context, currentUser, currentAccount);

        // Gán sự kiện cho các nút
        rutTienBtn.addActionListener(e -> controller.openWithdrawForm(this));
        napTienBtn.addActionListener(e -> controller.openDepositForm(this));
        chuyenKhoanBtn.addActionListener(e -> controller.openTransferForm(this));
        doiPinBtn.addActionListener(e -> controller.openPinForm(this));
        profileButton.addActionListener(e -> controller.openProfileForm(this));
        lichSuGiaoDichBtn.addActionListener(e -> controller.openTransactionHistoryForm(this));
    }
    /**
     * Hiển thị form chính.
     */
    @Override
    public void showForm() {
        this.setVisible(true);
    }

    /**
     * Gọi khi số dư thay đổi để cập nhật hiển thị.
     */
    @Override
    public void onBalanceChanged(BigDecimal newBalance) {
        SwingUtilities.invokeLater(() -> {
            balanceLabel.setText("Số dư: " + String.format("%,.0f VNĐ", newBalance));
        });
    }
}
