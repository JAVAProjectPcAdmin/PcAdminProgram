package AdminServer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable {
	private String name;
	private String userNumber;
	private String UserID;
	private String startTime;
	private String order;
	private long startTimeCalc;
	private int totalPrice = 0;
	private int addPrice = 0;
	private int seatNumber;

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

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

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getAddPrice() {
		return addPrice;
	}
	
	public void setAddPrice(int addPrice) {
		this.addPrice = addPrice;
	}
	
	public long getStartTimeCalc() {
		return startTimeCalc;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime() {
		this.startTimeCalc = System.currentTimeMillis();

		SimpleDateFormat dayTime = new SimpleDateFormat("MM-dd  HH:mm:ss");
		this.startTime = dayTime.format(new Date(startTimeCalc));
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrder() {
		return order;
	}

	public User(String name) {
		this.name = name;
		this.setStartTime();

	}

}
