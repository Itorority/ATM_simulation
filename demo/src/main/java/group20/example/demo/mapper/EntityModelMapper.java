package group20.example.demo.mapper;

import group20.example.demo.entity.Account;
import group20.example.demo.entity.User;
import group20.example.demo.model.AccountModel;
import group20.example.demo.model.UserModel;

public class EntityModelMapper {
    public static UserModel toUserModel(User entity) {
        if (entity == null)
            return null;
        UserModel model = new UserModel();
        model.setUserId(entity.getUserId());
        model.setFullName(entity.getFullName());
        model.setEmail(entity.getEmail());
        model.setPhoneNumber(entity.getPhoneNumber());
        model.setUserPassword(entity.getUserPassword());
        model.setDateOfBirth(entity.getDateOfBirth());
        return model;
    }

    public static AccountModel toAccountModel(Account entity) {
        if (entity == null)
            return null;
        AccountModel model = new AccountModel();
        model.setAccountNumber(entity.getAccountNumber());
        model.setPinHash(entity.getPinHash());
        model.setBalance(entity.getBalance());
        if (entity.getUserId() != null) {
            model.setUserId(entity.getUserId());
        }
        return model;
    }
}
