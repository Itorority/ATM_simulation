package group20.example.demo.controller;

import org.springframework.stereotype.Controller;

import group20.example.demo.entity.User;
import group20.example.demo.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Kiểm tra user có tồn tại hay không
    public boolean existsByEmailAndPassword(String email, String password) {
        return userService.existsByEmailAndPassword(email, password);
    }

    // tìm User 
    public User findByEmailAndPassword(String email, String password) {
        return userService.findByEmailAndPassword(email, password);
    }

    // Cập nhật email
    public void updateEmailById(Long id, String email) {
        userService.updateEmailById(id, email);
    }

    // Cập nhật password
    public void updatePasswordById(Long id, String password) {
        userService.updatePasswordById(id, password);
    }

    // Cập nhật số điện thoại
    public void updatePhoneNumberById(Long id, String phoneNumber) {
        userService.updatePhoneNumberById(id, phoneNumber);
    }

    // Rút tiền
    public void withdrawMoney(Long id, String pin, double money) {
        userService.withdrawMoney(id, pin, money);
    }

    // Nạp tiền
    public void depositMoney(Long id, String pin, double money) {
        userService.depositMoney(id, pin, money);
    }

    // Chuyển tiền
    public void transferMoney(Long userId, String pin, String accountNumber, double money) {
        userService.transferMoney(userId, pin, accountNumber, money);
    }
}
