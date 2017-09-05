package userLogin;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import AdminServer.User;

public class UserClient {
	Socket socket=null;
	ObjectOutputStream oos;
	
	public UserClient(User user) {
		try {
			socket = new Socket("127.0.0.1", 7777);
			System.out.println("서버랑 연결됬네?!");
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
