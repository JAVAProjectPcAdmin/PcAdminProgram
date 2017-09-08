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
	public static boolean newOrder = false;
	User user;
<<<<<<< HEAD
	

	public UserClient(User user) {
		try {

			socket = new Socket("70.12.115.54", 7777);
			//socket = new Socket("70.12.115.59", 7777);
=======
	public static boolean newOrder = false;
	User user;

	public UserClient(User user) {
		try {
			socket = new Socket("70.12.115.54", 7777);
			// socket = new Socket("70.12.115.59", 7777);
>>>>>>> bd7b89c828468c599ac7887b7fd088e8dd8b8cb0
=======

	public UserClient(User user) {
		try {
			socket = new Socket("70.12.115.54", 7777);
			// socket = new Socket("70.12.115.59", 7777);
>>>>>>> bd7b89c828468c599ac7887b7fd088e8dd8b8cb0

			System.out.println("서버랑 연결됬네?!");

			String ip = socket.getInetAddress() + "";
			System.out.println(ip);

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
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> bd7b89c828468c599ac7887b7fd088e8dd8b8cb0
=======

>>>>>>> bd7b89c828468c599ac7887b7fd088e8dd8b8cb0
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
