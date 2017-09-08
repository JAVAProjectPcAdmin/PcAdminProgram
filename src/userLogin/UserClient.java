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
			socket = new Socket("70.12.115.54", 7777);
<<<<<<< HEAD
=======
			//socket = new Socket("70.12.115.59", 7777);
>>>>>>> b138e81c3bf7d3e832de5b4a83a9da8bf7556cb4
			System.out.println("서버랑 연결됬네?!");
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
