package group20.example.demo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;

import group20.example.demo.controller.LoginController;
import group20.example.demo.service.AccountService;
import group20.example.demo.service.UserService;

/**
 * Lớp LoginForm đại diện cho giao diện đăng nhập của ứng dụng ATM.
 * Sử dụng Swing để xây dựng giao diện, tích hợp Spring để quản lý logic backend.
 */
public class LoginForm extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private final ApplicationContext context;

    public LoginForm(ApplicationContext context) {
        this.context = context;
        initUI(); // khởi tạo giao diện
    }

    private void initUI() {
        setTitle("ATM - Đăng nhập");
        setSize(800, 600);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(220, 220, 220));
        setContentPane(mainPanel);

        // ===== PHẦN TRÊN: Hiển thị logo và hotline =====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel labLogo = new JLabel("ATM Simulator");
        labLogo.setFont(new Font("Arial", Font.BOLD, 25));
        topPanel.add(labLogo);

        topPanel.add(Box.createHorizontalGlue());
        // Hotline hiển thị bên phải
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

        // ===== PHẦN GIỮA: Form đăng nhập =====
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(220, 220, 220));
        mainPanel.add(panel, BorderLayout.CENTER);

        // Tiêu đề
        JLabel titleLabel = new JLabel("ĐĂNG NHẬP ATM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBounds(270, 80, 300, 40);
        panel.add(titleLabel);

        // Tên tài khoản
        JLabel nameLabel = new JLabel("Tên tài khoản:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        nameLabel.setBounds(200, 180, 120, 30);
        panel.add(nameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(330, 180, 200, 30);
        panel.add(usernameField);

        // Mật khẩu
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordLabel.setBounds(200, 240, 120, 30);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(330, 240, 200, 30);
        panel.add(passwordField);

        // Nhãn "Quên mật khẩu"
        JLabel forgotPasswordLabel = new JLabel("Quên mật khẩu?");
        forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        forgotPasswordLabel.setBounds(430, 285, 140, 25);
        forgotPasswordLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(forgotPasswordLabel);

        // Sự kiện hover + click cho label quên mật khẩu
        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(panel, "Vui lòng liên hệ ngân hàng để lấy lại mật khẩu.");
            }

            public void mouseEntered(MouseEvent e) {
                forgotPasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
            }

            public void mouseExited(MouseEvent e) {
                forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            }
        });

        // Nút đăng nhập
        loginButton = new JButton("Đăng nhập");
        loginButton.setBounds(330, 330, 120, 40);
        loginButton.setFont(new Font("Arial", Font.BOLD, 15));
        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.WHITE);
        panel.add(loginButton);

        // Gắn sự kiện click cho nút
        loginButton.addActionListener(e -> onLogin());
    }

    /**
     * Xử lý đăng nhập khi người dùng nhấn nút "Đăng nhập".
     * Gọi controller để xác thực người dùng và mở MainForm nếu thành công.
     */
    private void onLogin() {
        String card = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Lấy các service từ Spring context
        UserService userService = context.getBean(UserService.class);
        AccountService accountService = context.getBean(AccountService.class);

        LoginController loginController = new LoginController(userService, accountService);

        try {
            loginController.login(card, password, context); // thực hiện login
            dispose();
        } catch (IllegalArgumentException e) {
            // Nếu đăng nhập thất bại, hiện dialog lỗi
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
