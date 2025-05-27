package group20.example.demo.controller;

import org.springframework.context.ApplicationContext;

import group20.example.demo.entity.Account;
import group20.example.demo.entity.User;
import group20.example.demo.mapper.EntityModelMapper;
import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;
import group20.example.demo.service.AccountService;
import group20.example.demo.service.UserService;
import group20.example.demo.view.MainForm;

public class LoginController {

    private final UserService userService;
    private final AccountService accountService;

    public LoginController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    /**
     * Kiểm tra xác thực tài khoản dựa trên email và mật khẩu.
     * @param email email hoặc mã số thẻ người dùng
     * @param password mật khẩu người dùng
     * @return true nếu tồn tại user với email và mật khẩu tương ứng, false nếu không
     */
    public boolean authenticate(String email, String password) {
        return userService.existsByEmailAndPassword(email, password);
    }

    /**
     * Thực hiện đăng nhập: nếu xác thực thành công thì tải dữ liệu user và account,
     * chuyển đổi sang model rồi hiển thị MainForm.
     * Nếu sai thông tin thì ném lỗi IllegalArgumentException.
     * @param email email hoặc mã số thẻ
     * @param password mật khẩu
     * @param context ApplicationContext của Spring để truyền cho MainForm
     */
    public void login(String email, String password, ApplicationContext context) {
        if (authenticate(email, password)) {
            // Lấy entity User dựa trên email và mật khẩu
            User userEntity = userService.findByEmailAndPassword(email, password);
            // Lấy entity Account dựa trên userId của User
            Account accountEntity = accountService.findAccountById(userEntity.getUserId());

            // Chuyển entity User sang model UserModel để sử dụng trong UI
            UserModel userModel = EntityModelMapper.toUserModel(userEntity);
            // Chuyển entity Account sang model AccountModel
            AccountModel accountModel = EntityModelMapper.toAccountModel(accountEntity);

            MainForm mainForm = MainForm.getInstance(context, userModel, accountModel);
            mainForm.setLocationRelativeTo(null);  
            mainForm.setVisible(true);            
        } else {
            // Nếu không đúng thông tin đăng nhập, ném ngoại lệ
            throw new IllegalArgumentException("Sai mã số thẻ hoặc mật khẩu!");
        }
    }
}
