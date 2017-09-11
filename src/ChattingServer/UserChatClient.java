package ChattingServer;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class UserChatClient extends Frame implements ActionListener {
	TextField tf;
	TextArea ta;
	Button b;
	List list;
	BufferedReader br;
	BufferedWriter bw;
	String id;

	public UserChatClient(String id) throws Exception {
		super(id + "님의 채팅창입니다");
		this.id = id;

		tf = new TextField(15);
		ta = new TextArea();
		b = new Button("나가기");
		list = new List();

		Panel p1 = new Panel();
		Panel p2 = new Panel();
		p1.setLayout(new BorderLayout());
		p2.setLayout(new BorderLayout());
		p1.add(ta);
		p2.add(tf);
		p2.add(b, "East");
		add(p1, "Center");
		add(p2, "South");

		tf.addActionListener(this);
		b.addActionListener(this);

		initNet();
		setBounds(200, 200, 500, 400);
		setVisible(true);
		readMsg(); // 상대방 읽어오는 메소드
	}

	public void initNet() throws Exception {
		Socket socket = new Socket("localhost", 8877);
		
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os);
		bw = new BufferedWriter(osw);
		
		sendMsg("enter/" + id); //아이디 보내는 send
	}

	public void readMsg() {
		try {
			while (true) {
				String line = br.readLine();
				System.out.println(line);
				String array[] = line.split("/");
<<<<<<< HEAD
				switch (array[0]) {

				case "귓속말":
					String arr[] = array[1].split("&");
					ta.append("[" + arr[0] + "->" + arr[1] + "]" + arr[2] + "\n");
					break;

				}
=======
				String arr[] = array[1].split("&");
				ta.append("[" + arr[1] + "]" + arr[2] + "\n");
>>>>>>> 6e432b3ac636af95798c15ae2ca4ff7c923e2003
			}
		} catch (Exception e) {
			System.out.println("읽다가오류남~");
		}
	}

	public void sendMsg(String msg) throws Exception {
		bw.write(msg + "\n");
		bw.flush();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == tf) {
			try {
				sendMsg("귓속말/" + "User" + "&" + "Admin" + "&" + tf.getText());
				ta.append("[User]->[Admin] " + tf.getText() + "\n");
				tf.setText("");

			} catch (Exception ee) {
				System.out.println("보내다가오류남~");
			}
		} else if (e.getActionCommand().equals("나가기")) {
			try {
				System.exit(0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public static void main(String args[]) throws Exception {
		UserChatClient client = new UserChatClient("User");

	}
}
