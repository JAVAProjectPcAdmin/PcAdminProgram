package userLogin;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import AdminServer.User;
import sun.net.InetAddressCachePolicy;

public class UserClient {
	Socket socket=null;
	ObjectOutputStream oos;
	
	public UserClient(User user) {
		try {
<<<<<<< HEAD
			socket = new Socket("70.12.115.59", 7777);
//			socket = new Socket("70.12.115.59", 7777);
=======
			socket = new Socket("70.12.115.54", 7777);
			//socket = new Socket("70.12.115.59", 7777);
>>>>>>> c6bc7d065994edc39dfa4e8bf40df6e7e43b5eb7
			
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
	
}
