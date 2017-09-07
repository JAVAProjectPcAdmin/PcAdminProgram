package chatServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChatServer {
	private ServerSocket serverSocket;
	private Socket socket;

	private Map<String, DataOutputStream> clientMap = new HashMap<String, DataOutputStream>();

	public void ServerSetting() {

		Collections.synchronizedMap(clientMap);
		try {
			while (true) {

				serverSocket = new ServerSocket(8888);
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress());
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
	public static void main(String[] args) {
		ChatServer cs = new ChatServer();
		cs.ServerSetting();
	}
}
