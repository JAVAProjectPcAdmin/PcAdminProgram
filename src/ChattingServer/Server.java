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
		System.out.println("접속자수:" + list.size());
	}

	public void talkMsg(String talk, String talk2, String talk3) {
		// talk 보낸사람
		// 2 받을사
		// 3 할말
		for (Guest g : list) {
			if (g.id.equals(talk2)) {

				try {
					g.sendMsg("귓속말/" + talk + "&" + talk2 + "&" + talk3);
				} catch (Exception e) {
					System.out.println("게스트에서 귓말보내다가 에러" + e.getMessage());
				}
			}
		}

	}

	void removeGuest(Guest g) {
		list.remove(g);
		System.out.println("접속자수:" + list.size());
	}

	void broadcast(String msg) throws Exception {
		for (Guest g : list) {
			g.sendMsg(msg);
		}
	}

	void makeGuestlist() throws Exception { // guestlist/홍길동/김길동/이길동/

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
					System.out.println(line + "읽음");
					String array[] = line.split("/"); /// /(슬래쉬)를 기준으로 문자열을 분해해서 배열에 저장.
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

					case "귓속말":
						String[] talk = array[1].split("&");
						server.talkMsg(talk[0], talk[1], talk[2]);
						// talk[0] 보낼사람
						// talk[1] 할말

					}
				}
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println(e.getMessage() + "겟메시지");
				System.out.println(id + "님 읽다가 에러남ㅠㅠ");

				// server.removeGuest(this);
				try {
					server.broadcast("exit/" + id + "나갓다");
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
