package group20.example.demo.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;

import group20.example.demo.controller.DepositController;
import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;
import group20.example.demo.service.AccountService;
import group20.example.demo.service.UserService;

public class DepositPINForm extends JFrame implements IForm {

    private JTextField textPIN;
    private ApplicationContext context;
    private UserModel currentUser;
    private AccountModel currentAccount;
    private double amount;

    public DepositPINForm(ApplicationContext context, UserModel currentUser, AccountModel currentAccount, double amount) {
        super();
        this.context = context;
        this.currentUser = currentUser;
        this.currentAccount = currentAccount;
        this.amount = amount;
        initUI();
    }

    private void initUI() {
        setTitle("ATM - Nhập mã PIN để nạp tiền");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 600);
        setResizable(false);

        JPanel jpMain = new JPanel(new BorderLayout());
        jpMain.setBackground(new Color(220, 220, 220));
        setContentPane(jpMain);

        // Top
        JPanel jpTop = new JPanel();
        jpTop.setLayout(new BoxLayout(jpTop, BoxLayout.X_AXIS));
        jpTop.setOpaque(false);
        jpTop.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel labLogo = new JLabel("ATM Simulator");
        labLogo.setFont(new Font("Arial", Font.BOLD, 25));
        jpTop.add(labLogo);
        jpTop.add(Box.createHorizontalGlue());

        JPanel jpHotline = new JPanel();
        jpHotline.setLayout(new BoxLayout(jpHotline, BoxLayout.Y_AXIS));
        jpHotline.setOpaque(false);
        jpHotline.add(new JLabel("HOTLINE ATM"));
        jpHotline.add(new JLabel("1900 1010 - 1010 1900"));
        jpTop.add(jpHotline);

        // Center
        JPanel jpCenter = new JPanel();
        jpCenter.setOpaque(false);
        jpCenter.setLayout(new BoxLayout(jpCenter, BoxLayout.Y_AXIS));
        jpCenter.setBorder(new EmptyBorder(20, 30, 20, 30));
        jpCenter.add(Box.createVerticalGlue());

        JLabel labContent1 = new JLabel("Xin vui lòng nhập mã PIN để nạp tiền");
        labContent1.setFont(new Font("Arial", Font.BOLD, 38));
        labContent1.setAlignmentX(CENTER_ALIGNMENT);

        JLabel labContent2 = new JLabel("(Khách hàng vui lòng che tay khi nhập mã PIN)");
        labContent2.setFont(new Font("Arial", Font.PLAIN, 30));
        labContent2.setForeground(Color.RED);
        labContent2.setAlignmentX(CENTER_ALIGNMENT);

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

        // Bottom
        JPanel jpBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jpBottom.setOpaque(false);
        jpBottom.setBorder(new EmptyBorder(20, 30, 20, 30));

        JButton btnContinue = createButton("Tiếp tục");
        JButton btnCancel = createButton("Huỷ bỏ");

        btnContinue.addActionListener(e -> onButtonContinue());
        btnCancel.addActionListener(e -> onButtonCancel());

        JPanel jpBtn = new JPanel();
        jpBtn.setOpaque(false);
        jpBtn.setLayout(new BoxLayout(jpBtn, BoxLayout.Y_AXIS));
        jpBtn.add(btnContinue);
        jpBtn.add(Box.createRigidArea(new Dimension(0, 10)));
        jpBtn.add(btnCancel);

        jpBottom.add(jpBtn);

        jpMain.add(jpTop, BorderLayout.NORTH);
        jpMain.add(jpCenter, BorderLayout.CENTER);
        jpMain.add(jpBottom, BorderLayout.SOUTH);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setBackground(Color.BLUE);
        btn.setForeground(Color.WHITE);
        btn.setPreferredSize(new Dimension(150, 45));
        return btn;
    }

    private void onButtonContinue() {
        String inputPIN = textPIN.getText().trim();

        try {
            if (!inputPIN.equals(currentAccount.getPinHash())) {
                throw new IllegalArgumentException("Mã PIN không đúng. Vui lòng thử lại.");
            }

            // Lấy controller từ Spring context
            DepositController depositController = new DepositController(
                context.getBean(UserService.class),
                context.getBean(AccountService.class)
            );

            // Gọi nạp tiền
            AccountModel updatedAccount = depositController.confirmDeposit(currentUser, currentAccount, amount);

            JOptionPane.showMessageDialog(this, "Nạp tiền thành công!");

            MainForm mainForm = MainForm.getInstance(context, currentUser, updatedAccount);
            mainForm.setVisible(true);
            mainForm.setLocationRelativeTo(null);
            dispose();

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onButtonCancel() {
        DepositForm backToDeposit = new DepositForm(context, currentUser, currentAccount);
        backToDeposit.setVisible(true);
        backToDeposit.setLocationRelativeTo(null);
        dispose();
    }

    @Override
    public void showForm() {
        this.setVisible(true);
    }
}
