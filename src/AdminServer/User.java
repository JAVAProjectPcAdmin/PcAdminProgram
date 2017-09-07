package AdminServer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable {
	private String name;
	private String userNumber;
	private String UserID;
	private String startTime;
	private long startTimeCalc;
	private int totalPrice=0;
	private int addPrice=0;
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

	public long getStartTimeCalc() {
		return startTimeCalc;
	}

	public String getStartTime() {
		return startTime;
	}

	public User(String name) {
		this.name = name;
		this.startTimeCalc = System.currentTimeMillis();
		
		SimpleDateFormat dayTime = new SimpleDateFormat("MM-dd  HH:mm:ss");
		startTime = dayTime.format(new Date(startTimeCalc));
	}
	
}
