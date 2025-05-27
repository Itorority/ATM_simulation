package group20.example.demo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;

import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;

/*
 * WithDrawForm là màn hình dùng để thực hiện giao dịch rút tiền
 * Hiển thị các nút rút tiền với số tiền có sẵn
 */
public class WithDrawForm extends JFrame implements IForm {

	private ApplicationContext context;
	private UserModel currentUser;
	private AccountModel currentAccount;

	public WithDrawForm(ApplicationContext context, UserModel currentUser, AccountModel currentAccount) {
		this.context = context;
		this.currentUser = currentUser;
		this.currentAccount = currentAccount;
		initUI();

	}

	private void initUI() {
		setTitle("ATM - Rút tiền");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		// Panel chính sử dụng BorderLayout
		JPanel jpMain = new JPanel(new BorderLayout());
		jpMain.setBackground(new Color(220, 220, 220));
		setContentPane(jpMain);

		// ====== Panel trên hiển thị Logo và Hotline =====
		JPanel jpTop = new JPanel();
		jpTop.setLayout(new BoxLayout(jpTop, BoxLayout.X_AXIS));
		jpTop.setOpaque(false);
		jpTop.setBorder(new EmptyBorder(20, 30, 20, 30));
		
		// Panel logo
		JLabel labLogo = new JLabel("ATM Simulator");
		labLogo.setFont(new Font("Arial", Font.BOLD, 25));
		jpTop.add(labLogo);

		// Panel Hotline
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
		jpTop.add(Box.createHorizontalGlue());
		jpTop.add(jpHotline, BorderLayout.EAST);

		// ===== Panel trung tâm hiển thị nội dung và các button với số tiền cho sẵn =====
		JPanel jpCenter = new JPanel();
		jpCenter.setLayout(new BoxLayout(jpCenter, BoxLayout.Y_AXIS));
		jpCenter.setOpaque(false);
		jpCenter.setBorder(new EmptyBorder(20, 30, 20, 30));
		jpCenter.add(Box.createVerticalGlue());
		
		// Panel nội dung
		JLabel labContent = new JLabel();
		labContent.setText("Xin vui lòng chọn số tiền cần rút");
		labContent.setFont(new Font("Arial", Font.BOLD, 38));
		labContent.setAlignmentX(CENTER_ALIGNMENT);
		
		// Panel chứa các nút rút tiền
		JPanel jpButtonWithDraw = new JPanel();
		jpButtonWithDraw.setLayout(new BoxLayout(jpButtonWithDraw, BoxLayout.X_AXIS));
		jpButtonWithDraw.setOpaque(false);

		// Các nút rút tiền với số tiền cho sẵn
		JButton btn100k = createButton("100.000 VND");
		JButton btn200k = createButton("200.000 VND");
		JButton btn500k = createButton("500.000 VND");
		JButton btn1trieu = createButton("1.000.000 VND");
		JButton btn2trieu = createButton("2.000.000 VND");
		JButton btnCancel = createButton("Huỷ bỏ");
		
		// Gán sự kiện cho các nút
		addWithDrawAction(btn100k, 100000);
		addWithDrawAction(btn200k, 200000);
		addWithDrawAction(btn500k, 500000);
		addWithDrawAction(btn1trieu, 1000000);
		addWithDrawAction(btn2trieu, 2000000);
		btnCancel.addActionListener(e -> onButtonCancel());

		// Panel chứa 3 nút bên phải từ 100k -> 500k
		JPanel jpButtonLeft = new JPanel();
		jpButtonLeft.setOpaque(false);
		jpButtonLeft.setLayout(new BoxLayout(jpButtonLeft, BoxLayout.Y_AXIS));

		jpButtonLeft.add(btn100k);
		jpButtonLeft.add(Box.createVerticalStrut(35));
		jpButtonLeft.add(btn200k);
		jpButtonLeft.add(Box.createVerticalStrut(35));
		jpButtonLeft.add(btn500k);
		
		// Panel chứa 3 nút bên trái 1tr,2tr và nút huỷ bỏ
		JPanel jpButtonRight = new JPanel();
		jpButtonRight.setOpaque(false);
		jpButtonRight.setLayout(new BoxLayout(jpButtonRight, BoxLayout.Y_AXIS));

		jpButtonRight.add(btn1trieu);
		jpButtonRight.add(Box.createVerticalStrut(35));
		jpButtonRight.add(btn2trieu);
		jpButtonRight.add(Box.createVerticalStrut(35));
		jpButtonRight.add(btnCancel);

		jpButtonWithDraw.add(jpButtonLeft, BorderLayout.WEST);
		jpButtonWithDraw.add(Box.createHorizontalGlue());
		jpButtonWithDraw.add(jpButtonRight, BorderLayout.EAST);

		jpCenter.add(labContent);
		jpCenter.add(Box.createVerticalStrut(50));
		jpCenter.add(jpButtonWithDraw);

		jpCenter.add(Box.createVerticalGlue());

		// ===== Panel dưới chưa nội dung =====
		JPanel jpBottom = new JPanel();
		jpBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		jpBottom.setOpaque(false);

		JLabel labContentCancel = new JLabel();
		labContentCancel.setText("(Ấn huỷ bỏ để dừng giao dịch)");
		labContentCancel.setFont(new Font("Arial", Font.PLAIN, 20));
		jpBottom.add(labContentCancel);

		jpMain.add(jpTop, BorderLayout.NORTH);
		jpMain.add(jpCenter, BorderLayout.CENTER);
		jpMain.add(jpBottom, BorderLayout.SOUTH);

	}
	
	// tạo nút với các định dạng cho sẵn
	private JButton createButton(String text) {
		Dimension btnSize = new Dimension(250, 60);
		JButton btn = new JButton(text);
		btn.setFont(new Font("Arial", Font.PLAIN, 20));
		btn.setBackground(Color.BLUE);
		btn.setForeground(Color.WHITE);
		btn.setAlignmentX(CENTER_ALIGNMENT);
		btn.setMaximumSize(btnSize);
		btn.setPreferredSize(btnSize);
		btn.setMinimumSize(btnSize);
		return btn;

	}
	
	// tạo sự kiện cho các nút rút tiền với số tiền cho sẵn
	private void addWithDrawAction(JButton button, double amount) {
		button.addActionListener(e -> {
			ConfirmWithDrawForm confirmForm = new ConfirmWithDrawForm(context, currentUser, currentAccount, amount);
			confirmForm.setVisible(true);
			confirmForm.setLocationRelativeTo(null);
			dispose();
		});
	}
	
	// tạo sự kiện cho nút Cancel
	private void onButtonCancel() {
		MainForm mainForm = MainForm.getInstance(context, currentUser, currentAccount);
		mainForm.setLocationRelativeTo(null);
		mainForm.setVisible(true);
		dispose();
	}

	// hiển thị form chính
	@Override
	public void showForm() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}

}
