package group20.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group20.example.demo.entity.User;

@Service
public class UserService {
  public static List<User> usersData = new ArrayList<>();
  public static final UserService userService = new UserService();
  @Autowired
  private AccountService accountService;

  public UserService() {

  }

  public static UserService getInstance() {
    if (userService == null) {
      return new UserService();
    }
    return userService;

  }

  public static void insertData() {

    usersData.add(
        new User(1L, "Nguyen Van A", "nguyenvana@example.com", "0123456789", "password123", LocalDate.of(1995, 5, 10)));
    usersData.add(
        new User(2L, "Tran Thi B", "tranthib@example.com", "0987654321", "securepass456", LocalDate.of(1998, 8, 25)));
    usersData.add(new User(3L, "Le Van C", "levanc@example.com", "0911223344", "passC789", LocalDate.of(2000, 12, 1)));
    usersData.add(new User(4L, "Pham D", "phamd@example.com", "0909090909", "d_secret_001", LocalDate.of(1992, 7, 14)));
    usersData
        .add(new User(5L, "Hoang E", "hoange@example.com", "0888999777", "Estrongpass", LocalDate.of(1997, 3, 30)));

    usersData.add(
        new User(6L, "Đỗ Xuân Hậu", "21130349@st.hcmuaf.edu.vn", "0123456789", "21130349", LocalDate.of(2006, 12, 1)));
    usersData.add(
        new User(7L, "Ma Nguyên Vũ", "23130388@st.hcmuaf.edu.vn", "0123456789", "23130388", LocalDate.of(2006, 12, 1)));
    usersData.add(new User(8L, "Lê Quang Trường", "23130355@st.hcmuaf.edu.vn", "0123456789", "23130355",
        LocalDate.of(2006, 12, 1)));
    usersData.add(new User(9L, "Nguyên Khắc Trường Đạt", "23130049@st.hcmuaf.edu.vn", "0123456789", "23130049",
        LocalDate.of(2006, 12, 1)));
    usersData.add(new User(10L, "Phạm Trần Quốc Tú", "23130363@st.hcmuaf.edu.vn", "0123456789", "23130363",
        LocalDate.of(2006, 12, 1)));
    usersData.add(new User(11L, "Nguyễn Bảo Tâm", "23130286@st.hcmuaf.edu.vn", "0123456789", "23130286",
        LocalDate.of(2006, 12, 1)));
  }

  // Kiểm tra người dùng có tồn tại không dựa vào email và password
  /**
   * Kiểm tra người dùng có tồn tại không dựa vào email và password
   * 
   * @param email
   * @param password
   * @return
   */
  public Boolean existsByEmailAndPassword(String email, String password) {
    // return userRepository.existsByEmailAndUserPassword(email, password);
    return usersData.stream()
        .anyMatch(user -> user.getEmail().equals(email) && user.getUserPassword().equals(password));
  }

  public User findByEmailAndPassword(String email, String password) {
    // return userRepository.findByEmailAndUserPassword(email, password);
    return usersData.stream()
        .filter(user -> user.getEmail().equals(email) && user.getUserPassword().equals(password))
        .findFirst()
        .orElse(null);
  }

  /**
   * Thay đổi email người dùng dựa vào ID
   * 
   * @param id
   * @param email
   */
  public void updateEmailById(Long id, String email) {
    // userRepository.updateEmailById(id, email);
    usersData.stream()
        .filter(user -> user.getUserId().equals(id))
        .findFirst()
        .ifPresent(user -> user.setEmail(email));

  }

  /**
   * Thay đổi password người dùng dựa vào ID
   * 
   * @param id
   * @param password
   */
  public void updatePasswordById(Long id, String password) {
    // userRepository.updatePasswordById(id, password);
    usersData.stream()
        .filter(user -> user.getUserId().equals(id))
        .findFirst()
        .ifPresent(user -> user.setUserPassword(password));
  }

  /**
   * Thay đổi số điện thoại người dùng dựa vào ID
   * 
   * @param id
   * @param phoneNumber
   */
  public void updatePhoneNumberById(Long id, String phoneNumber) {
    // userRepository.updatePhoneNumberById(id, phoneNumber);
    usersData.stream()
        .filter(user -> user.getUserId().equals(id))
        .findFirst()
        .ifPresent(user -> user.setPhoneNumber(phoneNumber));
  }

  /**
   * Withdraw money from account by using id and muiltiply by -1
   * 
   * @param id
   * @param money
   * @param pin
   */
  public void withdrawMoney(Long id, String pin, double money) {
    try {
      this.accountService.updateBalanceByUserId(id, pin, -1 * money);

    } catch (Exception e) {
      throw new IllegalArgumentException("Withdraw failed: " + e.getMessage());
    }
  }

  /**
   * Deposit money from account by using id and muiltiply by 1
   * 
   * @param id
   * @param money
   * @param pin
   */
  public void depositMoney(Long id, String pin, double money) {
    try {
      this.accountService.updateBalanceByUserId(id, pin, 1 * money);
    } catch (Exception e) {
      throw new IllegalArgumentException("Deposit failed: " + e.getMessage());
    }
  }

  /**
   * Transfer money from account by using numberaccount and muiltiply by -1
   * 
   * @param userId        the user id that transfer money to another account
   * @param pin           the pin of user
   * @param accountNumber the account number that userId want to transfer money
   * @param money
   */

  public void transferMoney(Long userId, String pin, String accountNumber, double money) {
    try {
      this.accountService.updateBalanceByAccountNumber(userId, pin, accountNumber,
          money);
    } catch (Exception e) {
      throw new IllegalArgumentException("Transfer failed: " + e.getMessage());
    }
  }
}
