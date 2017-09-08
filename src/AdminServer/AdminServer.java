package AdminServer;

import java.io.BufferedWriter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminServer {
	ServerSocket serverSocket = null;
	List<Socket> clientSocket = new ArrayList<>();
	Socket adminSocket=null;
	BufferedWriter bw = null;
	User user2;
	ObjectInputStream clientInStream;
	ObjectOutputStream adminOutStream = null;

	public AdminServer() {
		try {
			serverSocket = new ServerSocket(7777);
			while (true) {
				Socket socket = serverSocket.accept(); // 기다림 - 연결되면 socket에 들어감

				if ((socket.getInetAddress()+"").equals("/70.12.115.54")) {
				//if ((socket.getInetAddress()+"").equals("/70.12.115.59")) {
					System.out.println("Admin client 연결");
					adminSocket = socket;			
					adminOutStream = new ObjectOutputStream(adminSocket.getOutputStream());
				} else {
					clientSocket.add(socket);
					
					clientInStream = new ObjectInputStream(socket.getInputStream());
					user2 = (User) clientInStream.readObject();
					String ip=socket.getInetAddress()+"";
					
					//70.12.115.59
					if(ip.substring(11).equals("53"))
						user2.setSeatNumber(2);
					else if(ip.substring(11).equals("54"))
						user2.setSeatNumber(3);
					else if(ip.substring(11).equals("60"))
						user2.setSeatNumber(1);
					else if(ip.substring(11).equals("59"))
						user2.setSeatNumber(10);
					
					adminOutStream.writeObject(user2);
					Thread.sleep(500);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new AdminServer();
	}
}
