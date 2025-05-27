package group20.example.demo.model;

import java.math.BigDecimal;

public interface ChangeListener {
	public void onBalanceChanged(BigDecimal newBalance);
}
