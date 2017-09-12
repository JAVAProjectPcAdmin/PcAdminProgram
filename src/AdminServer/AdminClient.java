package AdminServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import flagment.Flagment;

public class AdminClient {

	Socket socket = null;
	public static List<User> userlist=new ArrayList<User>();
	ObjectInputStream ois = null;

	public AdminClient() {
		try {
			socket = new Socket("70.12.115.53", 7777);
			System.out.println("드디어 연결!!");
			////////////////////////////////////////////////////////////////////////// 연결됨
			User user = null;
			AdminclientThread thread= new AdminclientThread(user, ois, socket);
			thread.start();
			
		} catch (IOException e) {   
			e.printStackTrace();
		}
	}

	class AdminclientThread extends Thread {
		User user;
		ObjectInputStream ois = null;
		Socket socket;

		public AdminclientThread(User user, ObjectInputStream ois, Socket socket) {
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
					user = (User) ois.readObject(); //새로 로그인한  User 객체 받아옴 
					
					for(int i=0;i<userlist.size();i++) {
						if(userlist.get(i).getSeatNumber()==user.getSeatNumber()) {// 이전에 있었던 유저면 이번에 order로 넘어온것
							userlist.set(i, user); //정보 새로 갱신
							Flagment.UserOrder[user.getSeatNumber()]=true;//메인 gui에 알림 
							System.out.println(Flagment.UserOrder[user.getSeatNumber()]+" , "+user.getSeatNumber());
							System.out.println(set);
							break;
						}
					}
					if(!set) {//새로운 유저일 경우
						System.out.println("new "+user.getName());
						user.setStartTime();
						userlist.add(user);
						System.out.println(userlist.size());
						Flagment.UserLoginState[user.getSeatNumber()] = true;
					}
				}
			} catch (IOException e) {
				
				
				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}