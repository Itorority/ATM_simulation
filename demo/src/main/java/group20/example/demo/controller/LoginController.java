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

    public boolean authenticate(String email, String password) {
        return userService.existsByEmailAndPassword(email, password);
    }

    public void login(String email, String password, ApplicationContext context) {
        if (authenticate(email, password)) {
            User userEntity = userService.findByEmailAndPassword(email, password);
            Account accountEntity = accountService.findAccountById(userEntity.getUserId());

            UserModel userModel = EntityModelMapper.toUserModel(userEntity);
            AccountModel accountModel = EntityModelMapper.toAccountModel(accountEntity);

            MainForm mainForm = MainForm.getInstance(context, userModel, accountModel);
            mainForm.setLocationRelativeTo(null);
            mainForm.setVisible(true);
        } else {
            throw new IllegalArgumentException("Sai mã số thẻ hoặc mật khẩu!");
        }
    }
}
