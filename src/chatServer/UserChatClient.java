package chatServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import AdminServer.User;

public class UserChatClient {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private String name;
	private String msg;
	User user;
	private UserChatGUI gui;

	public void connect() {
		try {
			socket = new Socket("70.12.115.60", 8888);
			System.out.println("¼­¹ö¿¬°áµÊ");
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			out.writeUTF(name);

			while (in != null) {
				msg = in.readUTF();
				gui.appendMsg(msg);

			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public final void setGUI(UserChatGUI gui) {
		this.gui = gui;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void sendMessage(String msg2) {
		try {
			out.writeUTF(msg2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		UserChatClient ucc = new UserChatClient();
		ucc.connect();
	}
}