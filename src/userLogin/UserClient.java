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

	public UserClient() {	//�α����ϸ� �����ڿ��� ������ ������ ������
		this.user= UserLoginGUI.user;
		try {
<<<<<<< HEAD
			socket = new Socket("70.12.115.60", 7777);	//�����ڿ� ����
=======
			socket = new Socket("70.12.115.54", 7777);	//�����ڿ� ����
>>>>>>> 005a0603f7a92b2a66663b397193d00e4938d47b
			System.out.println("������ ������?!");

			oos = new ObjectOutputStream((socket.getOutputStream()));

			oos.writeObject(user);
			oos.flush();
			Thread.sleep(1000);	//�α����� ������ ������ ��ü�� �뤊�� ��Ƽ� �ѱ�
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void orderTOAdmin() {	//�ֹ����� (���ο�����)�� ��ü�� ��Ƽ� �ٽ� �ѱ�
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
	}	//�ܺο��� ������Ʈ �� ���ο� ������ �޾ƿ��� ���� 
}
