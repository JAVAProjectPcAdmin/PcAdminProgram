package AdminServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import adminMain.LeftMainGUI;

public class AdminServer {
	ServerSocket serverSocket = null;
	List<Socket> clientSocket = new ArrayList<>();
	Socket adminSocket = null;

	User user2;
	ObjectInputStream clientInStream;
	ObjectOutputStream adminOutStream = null;
	private List<UserThread> threadList;

	LeftMainGUI lmGui;
	AdminClient adminClient;

	public AdminServer() {
		threadList = new ArrayList<>();
		try {
			serverSocket = new ServerSocket(7777);
			while (true) {
				Socket socket = serverSocket.accept(); // 기다림 - 연결되면 socket에 들어감
				System.out.println(socket.getInetAddress());

				if ((socket.getInetAddress() + "").equals("/70.12.115.53")) {
					// if ((socket.getInetAddress()+"").equals("/70.12.115.54")) {
					// if ((socket.getInetAddress()+"").equals("/70.12.115.59")) {
					System.out.println("Admin client 연결");
					adminSocket = socket;
					adminOutStream = new ObjectOutputStream(adminSocket.getOutputStream());
				} else {

					UserThread t = new UserThread(user2, socket);
					threadList.add(t);
					t.start();

				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class UserThread extends Thread {
		User user;
		Socket socket;

		public UserThread(User user, Socket socket) {
			this.user = user;
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				while (true) {
					clientInStream = new ObjectInputStream(socket.getInputStream());
					user = (User) clientInStream.readObject();

					String ip = socket.getInetAddress() + "";

					// 70.12.115.59
					if (ip.substring(11).equals("53"))
						user.setSeatNumber(2);
					else if (ip.substring(11).equals("54"))
						user.setSeatNumber(3);
					else if (ip.substring(11).equals("60"))
						user.setSeatNumber(1);
					else if (ip.substring(11).equals("59"))
						user.setSeatNumber(10);

					adminOutStream.writeObject(user);
					Thread.sleep(500);
				}
				// 연결 끊기
				//////////////////////////////////////////////////////////////////////////////////////////////////
			} catch (IOException e) {
				int tempSize = adminClient.userlist.size();
				lmGui = new LeftMainGUI();
				Object[] temp = new Object[4];

				System.out.println(tempSize);

				for (int i = 0; i < tempSize; i++) {
					if (user.getUserID().equals(adminClient.userlist.get(i).getUserID())) {
						temp[0] = adminClient.userlist.get(i).getName();
						System.out.println(temp[0]);
						temp[1] = adminClient.userlist.get(i).getUserID();
						System.out.println(temp[1]);
						temp[2] = adminClient.userlist.get(i).getSeatNumber();
						System.out.println(temp[2]);
						temp[3] = adminClient.userlist.get(i).getTotalPrice();
						System.out.println(temp[3]);

						lmGui.finishedModel.insertRow(0, temp);
						lmGui.finishedTable.updateUI();
					}else {
						System.out.println("else");
					}
						
				}

				removeThread(this);
				System.out.println("연결이 끊어졋다 !");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		public void removeThread(UserThread t) {
			threadList.remove(t);
		}

	}

	public static void main(String[] args) {
		new AdminServer();
	}
}
