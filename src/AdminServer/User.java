package AdminServer;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	private String name;
	private String date;
	private String totalPrice;
	private int seatNumber;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public User(String name) {
		this.name=name;
		Date time=new Date();
	}
}
