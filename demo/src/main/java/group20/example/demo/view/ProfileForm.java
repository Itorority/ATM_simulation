package group20.example.demo.view;

import java.awt.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;

import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;

/**
 * Giao diện hiển thị thông tin hồ sơ người dùng
 */
public class ProfileForm extends JFrame implements IForm {
    private ApplicationContext context;
    private MainForm mainForm;

    private UserModel user;
    private AccountModel account;

    private JLabel userLabel, userIDLabel, lblIdentity, lblEmail, lblChangePassword, lblBalance;
    private JButton exitButton, logoutButton;

    public ProfileForm(ApplicationContext context, MainForm mainForm, UserModel user, AccountModel account) {
        this.context = context;
        this.mainForm = mainForm;
        this.user = user;
        this.account = account;

        initUI(); 
        setUserInfo(user, account);
    }

    /**
     * Hiển thị thông tin người dùng và tài khoản lên các label
     */
    public void setUserInfo(UserModel user, AccountModel account) {
        userLabel.setText(user != null ? user.getFullName() : "Chưa rõ");
        userIDLabel.setText("User ID: " + (user != null ? user.getUserId() : "N/A"));
        lblEmail.setText("Email: " + (user != null ? user.getEmail() : "N/A"));
        lblIdentity.setText("SĐT: " + (user != null ? user.getPhoneNumber() : "N/A"));
        lblChangePassword.setText("Mã PIN: " + (account != null ? account.getPinHash() : "N/A"));

        // Định dạng số dư tài khoản
        if (account != null && account.getBalance() != null) {
            BigDecimal balance = account.getBalance();
            NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));
            String formattedBalance = nf.format(balance) + " VNĐ";
            lblBalance.setText("Số dư: " + formattedBalance);
        } else {
            lblBalance.setText("Số dư: 0 VNĐ");
        }
    }

    private void initUI() {
        setTitle("Hồ sơ người dùng");
        setLocationRelativeTo(mainForm);
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        // Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(220, 220, 220));
        setContentPane(mainPanel);

        // ===== Panel trên cùng: Logo và hotline =====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel labLogo = new JLabel("ATM Simulator");
        labLogo.setFont(new Font("Arial", Font.BOLD, 25));
        topPanel.add(labLogo);

        topPanel.add(Box.createHorizontalGlue());

        // Thông tin hotline
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

        // ===== Nội dung chính =====
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(20, 150, 20, 150));

        // Tiêu đề
        JLabel titleLabel = new JLabel("HỒ SƠ NGƯỜI DÙNG");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(20));

        // Panel hiển thị tên và ID
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        profilePanel.setBackground(Color.WHITE);
        profilePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        userLabel = new JLabel("Tên người dùng");
        userLabel.setFont(new Font("Arial", Font.BOLD, 25));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        userIDLabel = new JLabel("User ID");
        userIDLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        userIDLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        profilePanel.add(Box.createVerticalStrut(15));
        profilePanel.add(userLabel);
        profilePanel.add(Box.createVerticalStrut(5));
        profilePanel.add(userIDLabel);
        profilePanel.add(Box.createVerticalStrut(15));
        contentPanel.add(profilePanel);
        contentPanel.add(Box.createVerticalStrut(20));

        // ===== Thông tin chi tiết =====
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        infoPanel.setBackground(Color.WHITE);

        lblEmail = createInfoLabel("Email: ");
        lblIdentity = createInfoLabel("SĐT: ");
        lblChangePassword = createInfoLabel("Mã PIN: ");
        lblBalance = createInfoLabel("Số dư: ");

        infoPanel.add(lblEmail);
        infoPanel.add(createSeparator());
        infoPanel.add(lblIdentity);
        infoPanel.add(createSeparator());
        infoPanel.add(lblChangePassword);
        infoPanel.add(createSeparator());
        infoPanel.add(lblBalance);

        contentPanel.add(infoPanel);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // ===== Các nút điều hướng =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonPanel.setOpaque(false);

        exitButton = new JButton("Thoát");
        logoutButton = new JButton("Đăng xuất");

        Font btnFont = new Font("Arial", Font.BOLD, 18);
        exitButton.setFont(btnFont);
        logoutButton.setFont(btnFont);

        exitButton.setBackground(Color.BLUE);
        exitButton.setForeground(Color.WHITE);

        logoutButton.setBackground(Color.BLUE);
        logoutButton.setForeground(Color.WHITE);

        buttonPanel.add(exitButton);
        buttonPanel.add(logoutButton);

        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(buttonPanel);

        // ===== Xử lý sự kiện =====
        // Khi nhấn nút Thoát
        exitButton.addActionListener(e -> {
            this.dispose();        
            if (mainForm != null) {
                mainForm.showForm(); // quay lại màn hình chính
            }
        });

        // Khi nhấn nút Đăng xuất
        logoutButton.addActionListener(e -> {
            this.dispose();
            if (mainForm != null) {
                mainForm.dispose(); // đóng màn hình chính
            }

            // Mở lại màn hình đăng nhập
            LoginForm loginForm = new LoginForm(context);
            MainForm.resetInstance();
            loginForm.setLocationRelativeTo(null);
            loginForm.setVisible(true);
        });
    }

    // Tạo label hiển thị thông tin
    private JLabel createInfoLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        return label;
    }

    // Tạo dòng phân cách
    private Component createSeparator() {
        JPanel line = new JPanel();
        line.setPreferredSize(new Dimension(0, 1));
        line.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        line.setBackground(Color.GRAY);
        return line;
    }
    
    @Override
    public void showForm() {
        this.setVisible(true);
    }
}
