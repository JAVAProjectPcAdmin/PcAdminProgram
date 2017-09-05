package AdminServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class AdminClient {
	ServerSocket serverSocket = null;
	Socket socket = null;
	BufferedWriter bw = null;
	User user2;
	ObjectInputStream ois;
	public AdminClient() {
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("기다리는중....");
			socket = serverSocket.accept(); // 기다림 - 연결되면 socket에 들어감
			System.out.println("클라이언트 요청 들어옴 ");
			
			ois= new ObjectInputStream(socket.getInputStream());
			user2=(User)ois.readObject();
			System.out.println(user2.getName());
			if(user2.getSeatNumber()==1) {
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
