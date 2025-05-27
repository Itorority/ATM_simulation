package group20.example.demo.controller;

import group20.example.demo.model.UserModel;

public class ProfileController {

    private final UserController userController;

    public ProfileController(UserController userController) {
        this.userController = userController;
    }

    public void updateEmail(UserModel user, String newEmail) throws Exception {
        if (user == null) throw new IllegalArgumentException("User không được null");
        if (newEmail == null || newEmail.trim().isEmpty())
            throw new IllegalArgumentException("Email không hợp lệ");

        userController.updateEmailById(user.getUserId(), newEmail);
        user.setEmail(newEmail);
    }

    public void updatePassword(UserModel user, String newPassword) throws Exception {
        if (user == null) throw new IllegalArgumentException("User không được null");
        if (newPassword == null || newPassword.trim().isEmpty())
            throw new IllegalArgumentException("Password không hợp lệ");

        userController.updatePasswordById(user.getUserId(), newPassword);
        user.setUserPassword(newPassword);
    }
}
