package group20.example.demo.mapper;

import group20.example.demo.entity.Account;
import group20.example.demo.entity.Transaction;
import group20.example.demo.entity.User;
import group20.example.demo.model.AccountModel;
import group20.example.demo.model.TransactionModel.TransactionType;
import group20.example.demo.model.TransactionModel;
import group20.example.demo.model.UserModel;

import java.util.List;
import java.util.stream.Collectors;

public class EntityModelMapper {

    /**
     * Chuyển đổi từ Entity User sang UserModel
     * 
     * @param entity đối tượng User từ database
     * @return UserModel tương ứng hoặc null nếu entity là null
     */
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

    /**
     * Chuyển đổi từ Entity Account sang AccountModel
     * 
     * @param entity đối tượng Account từ database
     * @return AccountModel tương ứng hoặc null nếu entity là null
     */
    public static AccountModel toAccountModel(Account entity) {
        if (entity == null)
            return null;
        AccountModel model = new AccountModel();
        model.setAccountNumber(entity.getAccountNumber());
        model.setPinHash(entity.getPinHash());
        model.setBalance(entity.getBalance());
        // Nếu userId có giá trị thì gán vào model
        if (entity.getUserId() != null) {
            model.setUserId(entity.getUserId());
        }
        return model;
    }

    /**
     * Chuyển đổi từ Entity Transaction sang TransactionModel
     * 
     * @param entity đối tượng Transaction từ database
     * @return TransactionModel tương ứng hoặc null nếu entity là null
     */
    public static TransactionModel toTransactionModel(Transaction entity) {
        if (entity == null)
            return null;

        TransactionType type;
        try {
            // Chuyển đổi chuỗi transactionType sang enum TransactionType, xử lý lỗi nếu không hợp lệ
            type = TransactionType.valueOf(entity.getTransactionType().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            type = null; // hoặc một giá trị mặc định nếu bạn muốn
        }

        // Khởi tạo TransactionModel với đầy đủ trường, bao gồm cả timestamp (dateCreated)
        TransactionModel model = new TransactionModel(
                entity.getTransactionId(),
                entity.getAccountNumber(),
                type,
                entity.getDateCreated(),  // thêm trường timestamp
                entity.getDescription()
        );

        return model;
    }

    /**
     * Chuyển đổi danh sách Entity Transaction sang danh sách TransactionModel
     * 
     * @param entities danh sách Transaction từ database
     * @return danh sách TransactionModel tương ứng
     */
    public static List<TransactionModel> toTransactionModelList(List<Transaction> entities) {
        return entities.stream()
                .map(EntityModelMapper::toTransactionModel)
                .collect(Collectors.toList());
    }
}
