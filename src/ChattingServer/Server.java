package ChattingServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

class Server {
	ArrayList<Guest> list;

	void initNet() throws Exception {
		list = new ArrayList<Guest>();
		ServerSocket ss = new ServerSocket(8877);
		while (true) {
			Socket s = ss.accept();
			Guest g = new Guest(this, s);
			g.start();
			addGuest(g);
		}
	}

	void addGuest(Guest g) {
		list.add(g);
		System.out.println("�����ڼ�:" + list.size());
	}

	public void talkMsg(String talk, String talk2, String talk3) {
		// talk �������
		// 2 ������
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

	void removeGuest(Guest g) {
		list.remove(g);
		System.out.println("�����ڼ�:" + list.size());
	}

	void broadcast(String msg) throws Exception {
		for (Guest g : list) {
			g.sendMsg(msg);
		}
	}

	void makeGuestlist() throws Exception { // guestlist/ȫ�浿/��浿/�̱浿/

		StringBuffer buffer = new StringBuffer("guestlist/");
		for (Guest g : list) {
			buffer.append(g.id + "/");
		}
		broadcast(buffer.toString());

	}

	class Guest extends Thread {
		String id;
		Server server;
		Socket socket;
		BufferedReader br;
		BufferedWriter bw;

		Guest(Server server, Socket socket) throws Exception {
			this.server = server;
			this.socket = socket;
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			bw = new BufferedWriter(osw);
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
						server.broadcast(line);
						break;
					case "msg":
						String str = "msg/[" + id + "]" + array[1];
						server.broadcast(str);
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
				System.out.println(id + "�� �дٰ� �������Ф�");

				// server.removeGuest(this);
				try {
					server.broadcast("exit/" + id + "������");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		public void sendMsg(String msg) throws Exception {
			bw.write(msg + "\n");
			bw.flush();
		}

	}

	public static void main(String args[]) throws Exception {
		Server server = new Server();
		server.initNet();
	}
}
