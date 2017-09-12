package AdminServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import adminMain.LeftMainGUI;
import flagment.Flagment;

public class AdminServer {
	ServerSocket serverSocket = null;
	Socket adminSocket = null;

	ObjectInputStream clientInStream;
	private List<UserThread> threadList;	//로그인한 유저들의 객체를 받기위해 쓰레드들을 담아놓는 리스트
	public static List<User> userlist = new ArrayList<User>();	//로그인해서 받아온 유저 객체들을 담아서 관리할 리스트
	
	LeftMainGUI lmGui;

	public AdminServer() {
		threadList = new ArrayList<>();
		try {
			serverSocket = new ServerSocket(7777);
				//시작하면  admin 쓰레드 한개 돌림
			
			while (true) { // 로그인하는 사람들에게 소켓을 계속 받아오기 위해 반복
				Socket socket = serverSocket.accept(); // 기다림 - 연결되면 socket에 들어감
				System.out.println(socket.getInetAddress());

				if (!(socket.getInetAddress() + "").equals("/70.12.115.54")) {	
					UserThread t = new UserThread(socket);
					threadList.add(t);
					t.start();
				}//사용자들이 각자의 컴퓨터에서 로그인을 하면 컴터마다 관리되는 쓰레드 생성
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class UserThread extends Thread {
		Socket socket;
		User user;

		public UserThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				while (true) {
					clientInStream = new ObjectInputStream(socket.getInputStream());
					user = (User) clientInStream.readObject();	//새로운 유저를 받아옴 
					String ip = socket.getInetAddress() + "";
					System.out.println(ip);

					boolean set=false; //새 유저인지 아닌지 구분	
					
					for(int i=0;i<userlist.size();i++) {
						System.out.println(userlist.size() + " : adminclient");
						if(userlist.get(i).getSeatNumber()==user.getSeatNumber()) {// 이전에 있었던 유저면 이번에 order로 넘어온것
							userlist.set(i, user); //정보 새로 갱신
							Flagment.UserOrder[user.getSeatNumber()]=true;//메인 gui에 알림 
							System.out.println(Flagment.UserOrder[user.getSeatNumber()]+" , "+user.getSeatNumber());
							set=true;
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
				// 연결 끊기
				//////////////////////////////////////////////////////////////////////////////////////////////////
			} catch (IOException e) {
				int tempSize = AdminServer.userlist.size();
				
				Flagment.UserLogout[user.getSeatNumber()]=true;
//				
			
				removeThread(this);
				
				e.printStackTrace();

				//////////////////////////////////////////////////////////////////////////////////////////////////
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void removeThread(UserThread t) {
		threadList.remove(t);
	}	//로그아웃하면 쓰레드도 종료

	public static void main(String[] args) {
		new AdminServer();
	}
	
}
