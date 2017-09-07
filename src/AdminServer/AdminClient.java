package AdminServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import flagment.Flagment;

public class AdminClient {

	Socket socket = null;
//	BufferedWriter bw = null;
	User user2;
	ObjectInputStream ois = null;

	public AdminClient() {
		try {

			socket = new Socket("70.12.115.59", 7777);
			System.out.println("드디어 연결!!");
			////////////////////////////////////////////////////////////////////////// 연결됨
			AdminclientThread thread= new AdminclientThread(user2, ois, socket);
			thread.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class AdminclientThread extends Thread {
		User user2;
		ObjectInputStream ois = null;
		Socket socket;

		public AdminclientThread(User user, ObjectInputStream ois, Socket socket) {
			// TODO Auto-generated constructor stub
			this.user2 = user;
			this.ois = ois;
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				while (true) {
					System.out.println("ois not null");
					user2 = (User) ois.readObject();
					System.out.println(user2.getName());

					Flagment.UserLoginState[user2.getSeatNumber()] = true;
					System.out.println("여기까지!!!!!");
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
