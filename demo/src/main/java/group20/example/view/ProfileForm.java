package group20.example.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class ProfileForm extends JFrame {
	public ProfileForm() {
		setTitle("ATM - Out of Service");
		setLocationRelativeTo(null);
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		initUI();
	}

	private void initUI() {
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
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setOpaque(false);
		contentPanel.setBorder(new EmptyBorder(20, 150, 20, 150));

		JLabel titleLabel = new JLabel("HỒ SƠ NGƯỜI DÙNG");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel profilePanel = new JPanel();
		profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
		profilePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		profilePanel.setBackground(Color.WHITE);

		JLabel userLabel = new JLabel("NGUYEN VAN A");
		userLabel.setFont(new Font("Arial", Font.BOLD, 25));
		userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel userIDLabel = new JLabel("User ID: 092122xx");
		userIDLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		userIDLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		profilePanel.add(Box.createVerticalStrut(10));
		profilePanel.add(Box.createVerticalStrut(10));
		profilePanel.add(userLabel);
		profilePanel.add(userIDLabel);
		profilePanel.add(Box.createVerticalStrut(10));

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		infoPanel.setBackground(Color.WHITE);

		JLabel lblIdentity = new JLabel("Giấy tờ tùy thân");
	    lblIdentity.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 20));
	    lblIdentity.setAlignmentX(Component.LEFT_ALIGNMENT);

	    JPanel lineIdentity = new JPanel();
	    lineIdentity.setPreferredSize(new Dimension(0, 1)); 
	    lineIdentity.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); 
	    lineIdentity.setBackground(Color.GRAY); 

	    JLabel lblEmail = new JLabel("Email");
	    lblEmail.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 20));
	    lblEmail.setAlignmentX(Component.LEFT_ALIGNMENT);

	    JPanel lineEmail = new JPanel();
	    lineEmail.setPreferredSize(new Dimension(0, 1)); 
	    lineEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); 
	    lineEmail.setBackground(Color.GRAY); 

	    JLabel lblChangePassword = new JLabel("Mã PIN");
	    lblChangePassword.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 20));
	    lblChangePassword.setAlignmentX(Component.LEFT_ALIGNMENT);

	    JPanel lineChangePassword = new JPanel();
	    lineChangePassword.setPreferredSize(new Dimension(0, 1)); 
	    lineChangePassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); 
	    lineChangePassword.setBackground(Color.GRAY);
	    
	    infoPanel.add(Box.createVerticalStrut(20));
	    infoPanel.add(lblIdentity);
	    infoPanel.add(lineIdentity);
	    infoPanel.add(Box.createVerticalStrut(20));
	    infoPanel.add(lblEmail);
	    infoPanel.add(lineEmail);
	    infoPanel.add(Box.createVerticalStrut(20));
	    infoPanel.add(lblChangePassword);
	    infoPanel.add(lineChangePassword);

		contentPanel.add(titleLabel);
		contentPanel.add(Box.createVerticalStrut(20));
		contentPanel.add(profilePanel);
		contentPanel.add(Box.createVerticalStrut(20));
		contentPanel.add(infoPanel);

		panel.add(contentPanel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new ProfileForm().setVisible(true);
		});
	}
}
