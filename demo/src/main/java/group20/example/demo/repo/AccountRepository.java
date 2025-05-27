package group20.example.demo.repo;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import group20.example.demo.entity.Account;
import jakarta.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  /**
   * lấy thông tin tài khoản dựa vào id người dùng
   * object
   * 
   * @param userId
   * @return
   */
  public boolean existsByUserId(Long userId);

  /**
   * lấy thông tin account by id
   * 
   * @param userId
   * 
   */
  public Account findByUserId(Long userId);

  /**
   * update balance
   * 
   * @param userId
   * @param balance
   * @return
   * 
   */
  @Modifying
  @Transactional
  @Query("UPDATE Account a SET a.balance = :balance WHERE a.userId = :userId")
  public void updateBalanceByUserId(@Param("userId") Long userId, @Param("balance") BigDecimal balance);

  /**
   * find account by account number
   * 
   * @param accountNumber
   * @return
   * 
   */
  @Query("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber")
  public Account findByAccountNumber(@Param("accountNumber") String accountNumber);

   /**
   * update pin
   * 
   * @param userId
   * @param newPIN
   * @return
   * 
   */
  @Modifying
  @Transactional
  @Query("UPDATE Account a SET a.pinHash = :newPin WHERE a.userId = :userId")
  public void updatePinByUserId(@Param("userId") Long userId, @Param("newPin") String newPin);
  
}
