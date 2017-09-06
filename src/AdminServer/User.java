package AdminServer;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private String name;
	private String userNumber;
	private String date;
	private String totalPrice;
	private String addPrice;
	private int seatNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDate() {
		return date;
	}

	public User(String name) {
		this.name = name;
		Date time = new Date();
	}

}
