package userLogin;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import AdminServer.User;

public class UserClient {
	Socket socket = null;
	ObjectOutputStream oos;
	public static boolean newOrder = false;
	User user;

	public Socket getSocket() {
		return socket;
	}

	public UserClient(User user) {
		try {
//			socket = new Socket("70.12.115.59", 7777);

<<<<<<< HEAD
			socket = new Socket("192.168.43.53", 7777);
=======
			socket = new Socket("70.12.115.59", 7777);
>>>>>>> 708d7cd30f5adf9de671e28e92d8743bf7deec8b
			System.out.println("������ ������?!");

			String ip = socket.getInetAddress() + "";
			System.out.println(ip);

			oos = new ObjectOutputStream((socket.getOutputStream()));

			oos.writeObject(user);
			oos.flush();
			Thread.sleep(1000);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void orderTOAdmin() {
		try {

			oos = new ObjectOutputStream((socket.getOutputStream()));
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
		this.user = user;
	}
}
