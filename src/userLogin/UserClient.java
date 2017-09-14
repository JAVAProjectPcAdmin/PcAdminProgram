package userLogin;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import AdminServer.User;

public class UserClient {
	Socket socket = null;
	ObjectOutputStream oos;
	public static boolean newOrder = false;
	User user;

	public Socket getSocket() {
		return socket;
	}

	public UserClient() { // 로그인하면 관리자에게 유저의 정보를 전달함
		this.user = UserLoginGUI.user;
		try {
			socket = new Socket("70.12.115.60", 7777); // 관리자와 연결
			System.out.println("서버랑 연결됬네?!");

			oos = new ObjectOutputStream((socket.getOutputStream()));

			oos.writeObject(user);
			oos.flush();
			Thread.sleep(1000); // 로그인한 유저의 정보를 객체에 통쨰로 담아서 넘김

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void orderTOAdmin() { // 주문정보 (새로운정보)를 객체에 담아서 다시 넘김
		try {

			oos = new ObjectOutputStream((socket.getOutputStream()));
			oos.writeObject(user);
			Thread.sleep(500);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void setUser(User user) {
		this.user = user;
	} // 외부에서 업데이트 된 새로운 정보를 받아오기 위해
}
