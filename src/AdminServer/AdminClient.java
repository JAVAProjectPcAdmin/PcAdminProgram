package AdminServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import flagment.Flagment;

public class AdminClient {

	Socket socket = null;
//	BufferedWriter bw = null;
	public static List<User> userlist=new ArrayList<User>();
	ObjectInputStream ois = null;

	public AdminClient() {
		try {

			socket = new Socket("70.12.115.59", 7777);
			System.out.println("드디어 연결!!");
			////////////////////////////////////////////////////////////////////////// 연결됨
			User user = null;
			AdminclientThread thread= new AdminclientThread(user, ois, socket);
			thread.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class AdminclientThread extends Thread {
		User user;
		ObjectInputStream ois = null;
		Socket socket;

		public AdminclientThread(User user, ObjectInputStream ois, Socket socket) {
			// TODO Auto-generated constructor stub
			this.user = user;
			this.ois = ois;
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				while (true) {
					user = (User) ois.readObject();
					System.out.println(user.getName());
					userlist.add(user);
					Flagment.UserLoginState[user.getSeatNumber()] = true;
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}