package AdminServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import flagment.Flagment;

public class AdminServer {
	ServerSocket serverSocket = null;
	Socket adminSocket = null;

	ObjectInputStream clientInStream;
	private List<UserThread> threadList;	//�α����� �������� ��ü�� �ޱ����� ��������� ��Ƴ��� ����Ʈ
	public static List<User> userlist = new ArrayList<User>();	//�α����ؼ� �޾ƿ� ���� ��ü���� ��Ƽ� ������ ����Ʈ
	

	public AdminServer() {
		System.out.println("���� ����");
		threadList = new ArrayList<>();
		try {
			serverSocket = new ServerSocket(7777);
				//�����ϸ�  admin ������ �Ѱ� ����
			
			while (true) { // �α����ϴ� ����鿡�� ������ ��� �޾ƿ��� ���� �ݺ�
				Socket socket = serverSocket.accept(); // ��ٸ� - ����Ǹ� socket�� ��
				System.out.println(socket.getInetAddress());

//				if (!(socket.getInetAddress() + "").equals("/70.12.115.53")) {	
					UserThread t = new UserThread(socket);
					threadList.add(t);
					t.start();
//				}//����ڵ��� ������ ��ǻ�Ϳ��� �α����� �ϸ� ���͸��� ���������� �޾ƿ��� ���������
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
					user = (User) clientInStream.readObject();	//���ο� ������ �޾ƿ� 
					String ip = socket.getInetAddress() + "";
					System.out.println(ip);

					boolean isNewUser=true; //�� �������� �ƴ��� ����	
					
					for(int i=0;i<userlist.size();i++) {
						if(userlist.get(i).getSeatNumber()==user.getSeatNumber()) {// ������ �־��� ������ �̹��� order�� �Ѿ�°�
							isNewUser=false;// ���ο� ������ �ƴ� 
							userlist.set(i, user); //���� ���� ����
							Flagment.UserOrder[user.getSeatNumber()]=true;//���� gui�� �˸� 
							break;
						}
					}
					
					if(isNewUser) {//���ο� ������ ���
						System.out.println("new "+user.getName());
						user.setStartTime();
						userlist.add(user);		//UserList�� �߰�
						Flagment.UserLoginState[user.getSeatNumber()] = true;	//���ο� ������ �α����ߴٰ� ����gui�� �˸� 
					}
				}
				// ���� ����
			} catch (IOException e) {
				int tempSize = AdminServer.userlist.size();
				
				Flagment.UserLogout[user.getSeatNumber()]=true;	//���� ������ �α׾ƿ��ߴٰ� ���� gui�� �˸�
			
				removeThread(this); //�α׾ƿ������� �����嵵 ����//���� ����Ʈ�� ���ο��� ����ؼ� ���� ���ܵ�
				e.printStackTrace();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void removeThread(UserThread t) {
		threadList.remove(t);
	}	

}
