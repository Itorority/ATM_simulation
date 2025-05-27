package group20.example.demo.view;

import java.awt.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;

import group20.example.demo.controller.ProfileController;
import group20.example.demo.controller.UserController;
import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;

public class ProfileForm extends JFrame implements IForm {
    private ApplicationContext context;
    private MainForm mainForm;

    private UserModel user;
    private AccountModel account;
    
    private ProfileController profileController;
    private UserController userController;


    private JLabel userLabel, userIDLabel, lblEmail, lblIdentity, lblPassword, lblPin, lblBalance;
    private JButton exitButton, logoutButton;
    private JButton btnEditEmail, btnEditPassword;

    public ProfileForm(ApplicationContext context, MainForm mainForm, UserModel user, AccountModel account) {
        this.context = context;
        this.mainForm = mainForm;
        this.user = user;
        this.account = account;
        this.userController = context.getBean(UserController.class);
        this.profileController = new ProfileController(userController);

        initUI();
        setUserInfo(user, account);
    }

    private void setUserInfo(UserModel user, AccountModel account) {
        userLabel.setText(user != null ? user.getFullName() : "Chưa rõ");
        userIDLabel.setText("User ID: " + (user != null ? user.getUserId() : "N/A"));
        lblEmail.setText("Email: " + (user != null ? user.getEmail() : "N/A"));
        lblIdentity.setText("SĐT: " + (user != null ? user.getPhoneNumber() : "N/A"));
        lblPassword.setText("Mật khẩu: " + (user != null ? "******" : "N/A"));
        lblPin.setText("Mã PIN: " + (account != null ? account.getPinHash() : "N/A"));

        if (account != null && account.getBalance() != null) {
            BigDecimal balance = account.getBalance();
            NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));
            lblBalance.setText("Số dư: " + nf.format(balance) + " VNĐ");
        } else {
            lblBalance.setText("Số dư: 0 VNĐ");
        }
    }

    private void initUI() {
        setTitle("Hồ sơ người dùng");
        setSize(800, 600);
        setLocationRelativeTo(mainForm);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(220, 220, 220));
        setContentPane(mainPanel);

        mainPanel.add(createTopPanel(), BorderLayout.NORTH);
        mainPanel.add(createContentPanel(), BorderLayout.CENTER);
        
        JScrollPane scrollPane = new JScrollPane(createContentPanel());
        scrollPane.setBorder(null);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        topPanel.setOpaque(false);

        JLabel logo = new JLabel("ATM Simulator");
        logo.setFont(new Font("Arial", Font.BOLD, 25));
        topPanel.add(logo);
        topPanel.add(Box.createHorizontalGlue());

        JPanel hotlinePanel = new JPanel();
        hotlinePanel.setLayout(new BoxLayout(hotlinePanel, BoxLayout.Y_AXIS));
        hotlinePanel.setOpaque(false);

        hotlinePanel.add(new JLabel("HOTLINE ATM"));
        hotlinePanel.add(new JLabel("1900 1010 - 1010 1900"));
        
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

        return topPanel;
    }

    private JPanel createContentPanel() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new EmptyBorder(20, 150, 20, 150));
        content.setOpaque(false);

        JLabel title = new JLabel("HỒ SƠ NGƯỜI DÙNG");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(title);
        content.add(Box.createVerticalStrut(20));
        content.add(createProfileBox());
        content.add(Box.createVerticalStrut(20));
        content.add(createInfoBox());
        content.add(Box.createVerticalStrut(20));
        content.add(createButtonBox());
        
        

        return content;
    }

    private JPanel createProfileBox() {
        JPanel profileBox = new JPanel();
        profileBox.setLayout(new BoxLayout(profileBox, BoxLayout.Y_AXIS));
        profileBox.setBackground(Color.WHITE);
        profileBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        profileBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        userLabel = new JLabel("Tên người dùng");
        userLabel.setFont(new Font("Arial", Font.BOLD, 25));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        userIDLabel = new JLabel("User ID");
        userIDLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        userIDLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        profileBox.add(Box.createVerticalStrut(15));
        profileBox.add(userLabel);
        profileBox.add(Box.createVerticalStrut(5));
        profileBox.add(userIDLabel);
        profileBox.add(Box.createVerticalStrut(15));

        return profileBox;
    }

    private JPanel createInfoBox() {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        lblEmail = new JLabel();
        btnEditEmail = createEditButton(() -> editEmail());
        box.add(createInfoRow(lblEmail, btnEditEmail));
        box.add(createSeparator());

        lblIdentity = createInfoLabel();
        box.add(createInfoRow(lblIdentity, null));
        box.add(createSeparator());

        lblPassword = new JLabel();
        btnEditPassword = createEditButton(() -> editPassword());
        box.add(createInfoRow(lblPassword, btnEditPassword));
        box.add(createSeparator());

        lblPin = createInfoLabel();
        box.add(createInfoRow(lblPin, null));
        box.add(createSeparator());

        lblBalance = createInfoLabel();
        box.add(createInfoRow(lblBalance, null));

        return box;
    }

    private JPanel createInfoRow(JLabel label, JButton button) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        row.setBorder(new EmptyBorder(10, 10, 10, 10));
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        row.add(label, BorderLayout.CENTER);
        if (button != null) {
            row.add(button, BorderLayout.EAST);
        }
        return row;
    }

    private JButton createEditButton(Runnable onClick) {
        JButton btn = new JButton("Sửa");
        btn.setFont(new Font("Arial", Font.PLAIN, 16));
        btn.setFocusable(false);
        btn.addActionListener(e -> onClick.run());
        return btn;
    }

    private JLabel createInfoLabel() {
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        return label;
    }

    private Component createSeparator() {
        JPanel line = new JPanel();
        line.setPreferredSize(new Dimension(0, 1));
        line.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        line.setBackground(Color.GRAY);
        return line;
    }

    private JPanel createButtonBox() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setOpaque(false);

        exitButton = new JButton("Thoát");
        logoutButton = new JButton("Đăng xuất");

        Font btnFont = new Font("Arial", Font.BOLD, 18);
        exitButton.setFont(btnFont);
        logoutButton.setFont(btnFont);

        exitButton.setBackground(Color.BLUE);
        exitButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.BLUE);
        logoutButton.setForeground(Color.WHITE);

        exitButton.addActionListener(e -> {
            dispose();
            if (mainForm != null) mainForm.showForm();
        });

        logoutButton.addActionListener(e -> {
            dispose();
            if (mainForm != null) mainForm.dispose();
            MainForm.resetInstance();
            LoginForm loginForm = new LoginForm(context);
            loginForm.setLocationRelativeTo(null);
            loginForm.setVisible(true);
        });

        panel.add(Box.createHorizontalGlue());
        panel.add(exitButton);
        panel.add(Box.createRigidArea(new Dimension(10, 0))); // khoảng cách nhỏ giữa 2 nút
        panel.add(logoutButton);

        return panel;
    }

    private void editEmail() {
        String newEmail = JOptionPane.showInputDialog(this, "Nhập email mới:", user.getEmail());
        if (newEmail != null) {
            newEmail = newEmail.trim();
            if (!newEmail.isEmpty() && !newEmail.equals(user.getEmail())) {
                try {
                    profileController.updateEmail(user, newEmail);
                    lblEmail.setText("Email: " + newEmail);
                    JOptionPane.showMessageDialog(this, "Cập nhật email thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật email: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }


    private void editPassword() {
        // Nhập mật khẩu cũ
        String oldPass = JOptionPane.showInputDialog(this, "Nhập mật khẩu cũ:");
        if (oldPass == null || !oldPass.equals(user.getUserPassword())) {
            JOptionPane.showMessageDialog(this, "Mật khẩu cũ không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Nếu mật khẩu cũ đúng, nhập mật khẩu mới
        String newPass = JOptionPane.showInputDialog(this, "Nhập mật khẩu mới:");
        if (newPass != null) {
            newPass = newPass.trim();
            if (!newPass.isEmpty() && !newPass.equals(user.getUserPassword())) {
                try {
                    profileController.updatePassword(user, newPass);
                    lblPassword.setText("Mật khẩu: ******");
                    JOptionPane.showMessageDialog(this, "Cập nhật mật khẩu thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật mật khẩu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }



    @Override
    public void showForm() {
        setVisible(true);
    }
}
