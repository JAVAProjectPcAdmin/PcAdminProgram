package ChattingServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.*;
import javax.swing.*;
public class Server {
	ArrayList<Guest> list;

	public void initNet() {
		list = new ArrayList<Guest>();
		ServerSocket ss;
		try {
			ss = new ServerSocket(8877);
			while (true) {
				Socket s = ss.accept();
				System.out.println("����");
				Guest g = new Guest(this, s);
				g.start();
				addGuest(g);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class Guest extends Thread {
		String id;
		Server server;
		Socket socket;
		BufferedReader br;
		BufferedWriter bw;

		public Guest(Server server, Socket socket) {
			this.server = server;
			this.socket = socket;
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void run() {
			try {
				while (true) {
					String line = br.readLine();
					System.out.println(line + "����");
					String array[] = line.split("/"); /// /(������)�� �������� ���ڿ��� �����ؼ� �迭�� ����.
					switch (array[0]) {
					case "enter":
						id = array[1];
						server.makeGuestlist();

						break;

					case "�ӼӸ�":
						String[] talk = array[1].split("&");
						server.talkMsg(talk[0], talk[1], talk[2]);
						// talk[0] �������
						// talk[1] �Ҹ�

					}
				}
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println(e.getMessage() + "�ٸ޽���");
				server.removeGuest(this); // ����Ʈ���� ������ �����Ӱ���.
				try {

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		public void sendMsg(String msg) {
			try {
				bw.write(msg + "\n");
				bw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void addGuest(Guest g) {
		list.add(g);
		System.out.println("�����ڼ�:" + list.size());
		
	}

	public void talkMsg(String talk, String talk2, String talk3) {
		// talk �������
		// 2 �������
		// 3 �Ҹ�
		for (Guest g : list) {
			if (g.id.equals(talk2)) {

				try {
					g.sendMsg("�ӼӸ�/" + talk + "&" + talk2 + "&" + talk3);
				} catch (Exception e) {
					System.out.println("�Խ�Ʈ���� �Ӹ������ٰ� ����" + e.getMessage());
				}
			}
		}

	}

	public void makeGuestlist() { // guestlist/ȫ�浿/��浿/�̱浿/

		StringBuffer buffer = new StringBuffer("guestlist/");
		for (Guest g : list) {
			buffer.append(g.id + "/");
		}

	}

	public void removeGuest(Guest g) {
		list.remove(g);
		System.out.println("�����ڼ�:" + list.size());
	}

	public static void main(String args[]) {
		Server server = new Server();
		server.initNet();
	}
}
