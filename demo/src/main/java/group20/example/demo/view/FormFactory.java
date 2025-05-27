package group20.example.demo.view;

import java.util.List;
import org.springframework.context.ApplicationContext;

import group20.example.demo.controller.TransactionController;
import group20.example.demo.model.TransactionModel;
import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;

/**
 * FormFactory chịu trách nhiệm tạo ra các form giao diện dựa trên loại form được yêu cầu.
 * Sử dụng mẫu thiết kế Factory Method để khởi tạo các màn hình tương ứng.
 */
public class FormFactory {

    /**
     * Phương thức tạo form tương ứng dựa trên FormType được truyền vào.
     *
     * @param type    Loại form cần khởi tạo (enum FormType)
     * @param context Spring ApplicationContext để lấy các bean cần thiết
     * @param user    Thông tin người dùng hiện tại
     * @param account Thông tin tài khoản hiện tại
     * @return Đối tượng IForm (thường là một JFrame) tương ứng
     */
    public static IForm createForm(FormType type, ApplicationContext context, UserModel user, AccountModel account) {
        switch (type) {
            case MAIN:
                // Trả về giao diện chính
                return new MainForm(context, user, account);

            case WITHDRAW:
                // Trả về form rút tiền
                return new WithDrawForm(context, user, account);

            case DEPOSIT:
                // Trả về form nạp tiền
                return new DepositForm(context, user, account);

            case TRANSFER:
                // Trả về form chuyển tiền
                return new MoneyTransferForm(context, user, account);

            case PIN:
                // Trả về form thay đổi mã PIN
                return new ChangePINForm(context, user, account);

            case PROFILE:
                // Trả về form xem hồ sơ cá nhân, cần truyền thêm MainForm
                return new ProfileForm(context, MainForm.getInstance(context, user, account), user, account);

            case HISTORY:
                // Trả về form lịch sử giao dịch
                TransactionController transactionController = context.getBean(TransactionController.class);

                // Lấy danh sách 10 giao dịch gần nhất từ controller
                List<TransactionModel> history = transactionController
                        .getTransactionModelsByAccountNumber(account.getAccountNumber(), 10);

                return new TransactionHistoryForm(context, history, user, account);

            default:
                // Nếu loại form không hợp lệ, ném ngoại lệ
                throw new IllegalArgumentException("Unknown form type: " + type);
        }
    }
}
