package group20.example.demo.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import group20.example.demo.entity.ATM;

@Service
public class ATMService {
  public static ATM atmData = new ATM(new BigDecimal("1000000.00"), 1, "active");
  @Autowired
  private final Long ATM_ID = 1L;
  public static final ATMService atmService = new ATMService();

  @Autowired
  public ATMService() {
  }

  public static ATMService getInstance() {
    if (atmService == null) {
      return new ATMService();
    }
    return atmService;
  }

  /**
   * Tìm ATM theo ID
   * 
   * @param atmId
   */
  public ATM findByAtmId() {
    return atmData;
  }

  /**
   * Update amount của ATM theo ID, cộng thêm amount truyền vào
   * 
   * @param atmId
   * @param amount số tiền cần cộng (Long)
   */
  @Transactional // bắt buộc có Transaction để thực thi câu update
  public void updateAmount(double amount) {
    ATM atm = findByAtmId();
    if (atm == null) {
      throw new RuntimeException("ATM không tồn tại với id: " + ATM_ID);
    }
    BigDecimal newAmount = atm.getAmount().add(BigDecimal.valueOf(amount));
    atm.setAmount(newAmount);
  }

  /**
   * check amount balance of the ATM
   * 
   * @return boolean
   */
  public boolean checkAmountATM(double money) {
    ATM atm = findByAtmId();

    BigDecimal moBigDecimal = new BigDecimal(money);
    // check if money < atm return fales
    boolean isvalid = atm.getAmount().compareTo(moBigDecimal) < 0;

    // return true;
    return !isvalid;
  }

}
