package userLogin;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import AdminServer.User;
import sun.net.InetAddressCachePolicy;
import userUsingState.UserUsingStateGUI;

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
<<<<<<< HEAD
			socket = new Socket("70.12.115.59", 7777);;
			
=======
<<<<<<< HEAD
//			socket = new Socket("70.12.115.54", 7777);
			 socket = new Socket("70.12.115.59", 7777);
=======

			socket = new Socket("70.12.115.54", 7777);
>>>>>>> 956ec137027ff7fc69e006e422a95ebe9d014e2b
			// socket = new Socket("70.12.115.59", 7777);

			System.out.println("서버랑 연결됬네?!");

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
