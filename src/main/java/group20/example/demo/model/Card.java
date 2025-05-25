package group20.example.demo.model;

public class Card {
	private String cardNumber; // Số thẻ
	private String accountNumber; // Số tài khoản liên kết
	private boolean isActive; // Trạng thái thẻ

	public Card(String cardNumber, String accountNumber) {
		this.cardNumber = cardNumber;
		this.accountNumber = accountNumber;
		this.isActive = true;
	}

	/**
	 * Kiểm tra thẻ còn hiệu lực không
	 */
	public boolean isValid() {
		return isActive;
	}

	/**
	 * Vô hiệu hóa thẻ
	 */
	public void deactivate() {
		isActive = false;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

}
