package userLogin;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import AdminServer.User;
import sun.net.InetAddressCachePolicy;

public class UserClient {
	Socket socket = null;
	ObjectOutputStream oos;
<<<<<<< HEAD
	public static boolean newOrder=false;
	User user;
	
=======

>>>>>>> ddcc51003c207bacef11fd9b078782ef1872da43
	public UserClient(User user) {
		try {

			socket = new Socket("70.12.115.59", 7777);
//			socket = new Socket("70.12.115.59", 7777);

			System.out.println("������ ������?!");
			
			String ip= socket.getInetAddress()+"";
			System.out.println(ip);

			oos=new ObjectOutputStream((socket.getOutputStream()));
			
			oos.writeObject(user);
			Thread.sleep(500);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
<<<<<<< HEAD
	public void orderTOAdmin() {
		try {

			oos=new ObjectOutputStream((socket.getOutputStream()));
			oos.writeObject(user);
			Thread.sleep(500);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void setUser(User user) {
		this.user=user;
	}
=======

>>>>>>> ddcc51003c207bacef11fd9b078782ef1872da43
}
