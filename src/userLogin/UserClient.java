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
			socket = new Socket("70.12.115.59", 7777);
			System.out.println("������ ������?!");
			String ip= socket.getInetAddress()+"";
			if(ip.equals("70.12.115.60"))
				user.setSeatNumber(1);
			oos=new ObjectOutputStream((socket.getOutputStream()));
			
			oos.writeObject(user);
			oos.flush();
			Thread.sleep(10000);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
