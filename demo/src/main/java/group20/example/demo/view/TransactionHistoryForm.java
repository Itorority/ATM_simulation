package group20.example.demo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;

import group20.example.demo.entity.Transaction;

public class TransactionHistoryForm extends JFrame {
	private List<Transaction> HistoryList;
	private final ApplicationContext context;

	public TransactionHistoryForm(ApplicationContext context) {
		this.context = context;
		initUI();
	}

	private void initUI() {
		setTitle("ATM - Out of Service");
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
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setOpaque(false);

		JLabel englishMessage = new JLabel("Transaction History");
		JLabel vietnameseMessage = new JLabel("Lịch sử giao dịch");

		englishMessage.setFont(new Font("Arial", Font.PLAIN, 25));
		englishMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		vietnameseMessage.setFont(new Font("Arial", Font.PLAIN, 25));
		vietnameseMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

		JComboBox<Transaction> transactionComboBox = new JComboBox<>();
		for (Transaction t : HistoryList) {
			transactionComboBox.addItem(t);
		}
		transactionComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
		transactionComboBox.setMaximumSize(new Dimension(400, 40));
		transactionComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

		contentPanel.add(Box.createVerticalStrut(20));
		contentPanel.add(englishMessage);
		contentPanel.add(vietnameseMessage);
		contentPanel.add(Box.createVerticalStrut(30));
		contentPanel.add(transactionComboBox);

		contentPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
		panel.add(contentPanel, BorderLayout.CENTER);
	}

	// public static void main(String[] args) {
	// SwingUtilities.invokeLater(() -> {
	// new TransactionHistoryForm().setVisible(true);
	// });
	// }
}
