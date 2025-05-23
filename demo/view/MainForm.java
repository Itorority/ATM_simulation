package group20.example.demo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;

public class MainForm extends JFrame {

    private static MainForm instance;
    private final ApplicationContext context;

    public MainForm(ApplicationContext context) {
    	
    	// Khao báo trường ApplicationContext
    	this.context = context;

        initUI();
    }

    public static MainForm getInstance(ApplicationContext context) {
        if (instance == null) {
            instance = new MainForm(context);
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

        JLabel labLogo = new JLabel("ATM Simulator");
        labLogo.setFont(new Font("Arial", Font.BOLD, 25));
        jpTop.add(labLogo, BorderLayout.WEST);

        JPanel jpHotline = new JPanel();
        jpHotline.setLayout(new BoxLayout(jpHotline, BoxLayout.Y_AXIS));
        jpHotline.setOpaque(false);

        JLabel labHot1 = new JLabel("HOTLINE ATM");
        labHot1.setFont(new Font("Arial", Font.PLAIN, 15));
        JLabel labHot2 = new JLabel("1900 1010 - 1010 1900");
        labHot2.setFont(new Font("Arial", Font.PLAIN, 15));
        jpHotline.add(labHot1);
        jpHotline.add(labHot2);

        jpTop.add(jpHotline, BorderLayout.EAST);
        panel.add(jpTop, BorderLayout.NORTH);

        // Content Panel
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

        // Button
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

        // Events
        /* rutTienBtn.addActionListener(e -> {
            WithDrawForm form = context.getBean(WithDrawForm.class);
            form.setLocationRelativeTo(null);
            form.setVisible(true);
        });
        chuyenKhoanBtn.addActionListener(e -> {
                    WithDrawForm form = context.getBean(WithDrawForm.class);
                    form.setLocationRelativeTo(null);
                    form.setVisible(true);
                });
        napTienBtn.addActionListener(e -> {
            WithDrawForm form = context.getBean(WithDrawForm.class);
            form.setLocationRelativeTo(null);
            form.setVisible(true);
        });
        doiPinBtn.addActionListener(e -> {
            WithDrawForm form = context.getBean(WithDrawForm.class);
            form.setLocationRelativeTo(null);
            form.setVisible(true);
        }); */
    }
}
