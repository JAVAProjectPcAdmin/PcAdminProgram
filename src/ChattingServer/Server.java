package ChattingServer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
<<<<<<< HEAD
import java.util.Scanner;
=======
import java.util.ArrayList;
import java.io.*;
import javax.swing.*;

public class Server {
	ArrayList<Guest> list;
>>>>>>> 9331ed2c8a3a91e31a08d016b7aa1d1e448d8379

public class Server {
	public static void main(String[] args) {
		ServerSocket serverSocket =null;
		Socket socket=null;
		String who="Admin";
		
		BufferedWriter bw = null;
		Scanner s = new Scanner(System.in);
		
		BufferedReader br =null;
		
		try {
<<<<<<< HEAD
			serverSocket= new ServerSocket(7777);
			while(true) {
				System.out.println("기다리는중....");
			socket= serverSocket.accept(); //기다림  - 연결되면 socket에 들어감 
			System.out.println("클라이언트 요청 들어옴 ");
			ClientFrame c= new ClientFrame(socket,who);
=======
			ss = new ServerSocket(8877);
			while (true) {

				Socket s = ss.accept();

				System.out.println("들어옴");
				new AdminChatClient("관리자");
				Guest g = new Guest(this, s);
				g.start();
				addGuest(g);
>>>>>>> 9331ed2c8a3a91e31a08d016b7aa1d1e448d8379
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
<<<<<<< HEAD
		
=======
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

					System.out.println(line + "읽음");
					String array[] = line.split("/"); /// /(슬래쉬)를 기준으로 문자열을 분해해서 배열에 저장.
					switch (array[0]) {
					case "enter":
						id = array[1];
						server.makeGuestlist();
						if (id.equals("User")) {
							new AdminChatClient("Admin");
						} else {
							
						}
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
				server.removeGuest(this); // 리스트에서 지워야 재접속가능.
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
		System.out.println("접속자수:" + list.size());

>>>>>>> 9331ed2c8a3a91e31a08d016b7aa1d1e448d8379
	}
}
