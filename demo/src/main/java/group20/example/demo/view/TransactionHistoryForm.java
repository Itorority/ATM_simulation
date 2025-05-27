package group20.example.demo.view;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.springframework.context.ApplicationContext;

import group20.example.demo.model.AccountModel;
import group20.example.demo.model.TransactionModel;
import group20.example.demo.model.UserModel;

public class TransactionHistoryForm extends JFrame implements IForm {
	
    private final List<TransactionModel> historyList;
    private final ApplicationContext context;
    private UserModel currentUser;
    private AccountModel currentAccount;
    
    public TransactionHistoryForm(ApplicationContext context, List<TransactionModel> historyList, UserModel currentUser, AccountModel currentAccount) {
        this.context = context;
        this.historyList = historyList != null ? historyList : List.of(); // Tránh null khi truyền vào
        this.currentUser = currentUser;
        this.currentAccount = currentAccount;
        initUI(); 
    }

    private void initUI() {
        setTitle("ATM - Transaction History");
        setLocationRelativeTo(null);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Panel chính chứa toàn bộ giao diện
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(220, 220, 220)); 
        setContentPane(mainPanel);

        // ===== Panel phía trên chứa logo và hotline =====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS)); 
        topPanel.setOpaque(false); 
        topPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel labLogo = new JLabel("ATM Simulator");
        labLogo.setFont(new Font("Arial", Font.BOLD, 25)); 
        topPanel.add(labLogo);

        topPanel.add(Box.createHorizontalGlue()); 

        // Panel nhỏ chứa 2 dòng hotline
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

        // ===== Phần nội dung chính =====
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(50, 30, 30, 30));  

        // Tiêu đề chính
        JLabel titleLabel = new JLabel("Transaction History / Lịch sử giao dịch");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Nếu không có dữ liệu giao dịch, hiển thị thông báo
        if (historyList.isEmpty()) {
            JLabel noDataLabel = new JLabel("Không có giao dịch nào.");
            noDataLabel.setFont(new Font("Arial", Font.ITALIC, 18));
            noDataLabel.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(noDataLabel, BorderLayout.CENTER);
        } else {
            // Nếu có giao dịch, tạo bảng để hiển thị
            // Tạo các cột và setup độ dài
            String[] columnNames = { "Mã giao dịch", "Số tài khoản", "Loại", "Thời gian", "Nội dung" };

            // Model dữ liệu bảng
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            // Định dạng hiển thị ngày giờ
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            // Duyệt danh sách giao dịch, thêm từng hàng vào model
            for (TransactionModel t : historyList) {
                String type = (t.getType() != null) ? t.getType().toString() : "UNKNOWN";
                String description = (t.getDescription() != null) ? t.getDescription() : "";
                String timestamp = (t.getTimestamp() != null) ? t.getTimestamp().format(formatter) : "N/A";

                Object[] rowData = {
                    t.getTransactionId(),
                    t.getAccountNumber(),
                    type,
                    timestamp,
                    description
                };

                tableModel.addRow(rowData);
            }

            // Tạo JTable từ model
            JTable table = new JTable(tableModel);
            table.setFont(new Font("Arial", Font.PLAIN, 16));
            table.setRowHeight(28);  // Chiều cao mỗi dòng
            table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
            table.setFillsViewportHeight(true);

            // Cho phép scroll ngang
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            // Thiết lập độ rộng từng cột để mô tả rõ ràng
            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(120);  // Transaction ID
            columnModel.getColumn(1).setPreferredWidth(150);  // Account Number
            columnModel.getColumn(2).setPreferredWidth(110);  // Type
            columnModel.getColumn(3).setPreferredWidth(140);  // Timestamp
            columnModel.getColumn(4).setPreferredWidth(500);  // Description

            // Đặt bảng vào JScrollPane để có thể cuộn khi nội dung lớn
            JScrollPane scrollPane = new JScrollPane(
                table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
            );
            scrollPane.setPreferredSize(new Dimension(1000, 400));

            contentPanel.add(scrollPane, BorderLayout.CENTER); 
        }

        mainPanel.add(contentPanel, BorderLayout.CENTER); // Đưa phần nội dung vào giữa mainPanel
        
        // ===== Panel phía dưới chứa nút hủy =====
        JPanel bottomPanel = new JPanel(new BorderLayout()); // Sử dụng BorderLayout để dễ căn chỉnh
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JButton btnCancel = new JButton("Hủy");
        btnCancel.setFont(new Font("Arial", Font.BOLD, 25));
        btnCancel.setPreferredSize(new Dimension(120, 40));
        btnCancel.setBackground(Color.BLUE);
        btnCancel.setForeground(Color.WHITE);

        // Xử lý sự kiện khi nhấn nút Hủy, quay về màn hình chính
        btnCancel.addActionListener(e -> {
            MainForm mainForm = MainForm.getInstance(context, currentUser, currentAccount);
            mainForm.setVisible(true);
            mainForm.setLocationRelativeTo(null);
            dispose(); // Đóng form hiện tại
        });

        // Tạo panel phụ để đẩy nút sang bên phải
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false);
        rightPanel.add(btnCancel);

        bottomPanel.add(rightPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH); 
    }

    @Override
    public void showForm() {
        this.setVisible(true);
    }
}
