package group20.example.demo.controller;

import org.springframework.context.ApplicationContext;

import group20.example.demo.service.UserService;
import group20.example.demo.view.MainForm;

public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    public boolean authenticate(String email, String password) {
        return userService.existsByEmailAndPassword(email, password);
    }

    public void login(String email, String password, ApplicationContext context) {
        if (authenticate(email, password)) {
            MainForm mainForm = MainForm.getInstance(context);
            mainForm.setLocationRelativeTo(null);
            mainForm.setVisible(true);
        } else {
            throw new IllegalArgumentException("Sai mã số thẻ hoặc mật khẩu!");
        }
    }
}
