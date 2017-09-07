package chatServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ChatServer {
	private ServerSocket serverSocket;
	private Socket socket;
	private String msg;

	private Map<String, DataOutputStream> clientMap = new HashMap<String, DataOutputStream>();

	public void ServerSetting() {

		Collections.synchronizedMap(clientMap);
		try {
			while (true) {

				serverSocket = new ServerSocket(8888);
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress());
				
				Receiver receiver = new Receiver(socket);
				receiver.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addClient(String name, DataOutputStream out) {
		sendMessage(name);
		clientMap.put(name, out);
	}

	public void removeClint(String name) {
		sendMessage(name);
		clientMap.remove(name);

	}

	public void sendMessage(String msg) {
		Iterator<String> it = clientMap.keySet().iterator();
		String key = "";
		while (it.hasNext()) {
			key = it.next();

			try {
				clientMap.get(key).writeUTF(msg);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	class Receiver extends Thread {
		private DataInputStream in;
		private DataOutputStream out;
		private String name;

		public Receiver(Socket socket) {

			try {
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());
				name = in.readUTF();
				addClient(name, out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void run() {
			try {
				while (in != null) {
					msg = in.readUTF();
					sendMessage(msg);

				}

			} catch (IOException e) {
				removeClint(name);
			}

		}

	}

	public static void main(String[] args) {
		ChatServer cs = new ChatServer();
		cs.ServerSetting();
	}
}
