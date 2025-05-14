package group20.example.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginForm extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginForm() {
        setTitle("ATM - Đăng nhập");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(220, 220, 220));
        setContentPane(mainPanel);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel labLogo = new JLabel("ATM Simulator");
        labLogo.setFont(new Font("Arial", Font.BOLD, 25));
        topPanel.add(labLogo);

        topPanel.add(Box.createHorizontalGlue());

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

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(220, 220, 220));
        mainPanel.add(panel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("ĐĂNG NHẬP ATM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBounds(270, 80, 300, 40);
        panel.add(titleLabel);

        JLabel nameLabel = new JLabel("Tên tài khoản:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        nameLabel.setBounds(200, 180, 120, 30);
        panel.add(nameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(330, 180, 200, 30);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordLabel.setBounds(200, 240, 120, 30);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(330, 240, 200, 30);
        panel.add(passwordField);

        JLabel forgotPasswordLabel = new JLabel("Quên mật khẩu?");
        forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        forgotPasswordLabel.setBounds(430, 285, 140, 25);
        forgotPasswordLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(forgotPasswordLabel);

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

        loginButton = new JButton("Đăng nhập");
        loginButton.setBounds(330, 330, 120, 40);
        loginButton.setFont(new Font("Arial", Font.BOLD, 15));
        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.WHITE);
        panel.add(loginButton);

        loginButton.addActionListener(e -> onLogin());
    }

    private void onLogin() {
        String card = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (card.equals("123456789") && password.equals("1234")) {
            dispose(); // đóng cửa sổ đăng nhập
            MainForm mainForm = MainForm.getInstance();
            mainForm.setLocationRelativeTo(null);
            mainForm.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Sai mã số thẻ hoặc password!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}
