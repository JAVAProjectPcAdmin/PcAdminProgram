package AdminServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import flagment.Flagment;

public class AdminClient {
		
		Socket socket = null;
		BufferedWriter bw = null;
		User user2;
		ObjectInputStream ois;

		public AdminClient() {
			try {
				
				socket = new Socket("127.0.0.1", 7777); 
				System.out.println("드디어 연결!!");
				ois = new ObjectInputStream(socket.getInputStream());
				user2 = (User) ois.readObject();
				System.out.println(user2.getName());

					Flagment.UserLoginState[user2.getSeatNumber()-1]=true;
					System.out.println("여기까지!!!!!");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

