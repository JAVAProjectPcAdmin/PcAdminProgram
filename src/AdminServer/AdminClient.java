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
			System.out.println("���� ����!!");
			////////////////////////////////////////////////////////////////////////// �����
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
					boolean set=false; //�� �������� �ƴ��� ����
					user = (User) ois.readObject(); //���� �α�����  User ��ü �޾ƿ� 
					
					for(int i=0;i<userlist.size();i++) {
						if(userlist.get(i).getSeatNumber()==user.getSeatNumber()) {// ������ �־��� ������ �̹��� order�� �Ѿ�°�
							userlist.set(i, user); //���� ���� ����
							Flagment.UserOrder[user.getSeatNumber()]=true;//���� gui�� �˸� 
							System.out.println(Flagment.UserOrder[user.getSeatNumber()]+" , "+user.getSeatNumber());
							System.out.println(set);
							break;
						}
					}
					if(!set) {//���ο� ������ ���
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