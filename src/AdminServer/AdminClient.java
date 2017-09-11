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
//			socket = new Socket("70.12.115.53", 7777);
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
					boolean set=false; //새 유저인지 아닌지 구분
					user = (User) ois.readObject();
					for(int i=0;i<userlist.size();i++) {
						if(userlist.get(i).getUserNumber().equals(user.getUserNumber())) {// 이전에 있었던 유저면 이번에 order로 넘어온것
							userlist.set(i, user); //정보 새로 갱신
							Flagment.UserOrder[user.getSeatNumber()]=true;//메인 gui에 알림 
							System.out.println(Flagment.UserOrder[user.getSeatNumber()]+" , "+user.getSeatNumber());
							set=true;
							System.out.println(set);
							break;
						}
					}
					if(!set) {
						System.out.println("new "+user.getName());
						user.setStartTime();
						userlist.add(user);
						Flagment.UserLoginState[user.getSeatNumber()] = true;
					}
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