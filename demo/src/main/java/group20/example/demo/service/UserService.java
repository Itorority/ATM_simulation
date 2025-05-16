package group20.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group20.example.demo.entity.User;
import group20.example.demo.repo.UserRepository;

@Service
public class UserService {

  @Autowired(required = true)
  private UserRepository userRepository;
  @Autowired
  private AccountService accountService;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
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
    return userRepository.existsByEmailAndUserPassword(email, password);
  }

  public User findByEmailAndPassword(String email, String password) {
    return userRepository.findByEmailAndUserPassword(email, password);
  }

  /**
   * Thay đổi email người dùng dựa vào ID
   * 
   * @param id
   * @param email
   */
  public void updateEmailById(Long id, String email) {
    userRepository.updateEmailById(id, email);

  }

  /**
   * Thay đổi password người dùng dựa vào ID
   * 
   * @param id
   * @param password
   */
  public void updatePasswordById(Long id, String password) {
    userRepository.updatePasswordById(id, password);
  }

  /**
   * Thay đổi số điện thoại người dùng dựa vào ID
   * 
   * @param id
   * @param phoneNumber
   */
  public void updatePhoneNumberById(Long id, String phoneNumber) {
    userRepository.updatePhoneNumberById(id, phoneNumber);
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
      this.accountService.updateBalanceByAccountNumber(userId, pin, accountNumber, money);
    } catch (Exception e) {
      throw new IllegalArgumentException("Transfer failed: " + e.getMessage());
    }
  }
}
