package ChattingServer;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.*;

public class AdminChatClient extends Frame implements ActionListener {
	JTextField tf;
	JTextArea ta;
	JButton b;
	List list;
	BufferedReader br;
	BufferedWriter bw;
	String id;

	public AdminChatClient(String id) throws Exception {
		super(id + "님의 채팅창입니다");
		this.id = id;

		tf = new JTextField(15);
		ta = new JTextArea();
		b = new JButton("나가기");

		list = new List();
		

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();

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
		readMsg();

	}

	public void initNet() throws Exception {
		Socket socket = new Socket("localhost", 8877);
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os);
		bw = new BufferedWriter(osw);
		sendMsg("enter/" + id);
	}

	public void readMsg() {
		try {
			while (true) {
				String line = br.readLine();
				System.out.println(line);
				String array[] = line.split("/");
				switch (array[0]) {
				case "귓속말":
					String arr[] = array[1].split("&");
					ta.append("[" + arr[0] + "->" + arr[1] + "]" + arr[2] + "\n");
					break;

				}
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
				sendMsg("귓속말/" + "Admin" + "&" + "User" + "&" + tf.getText());
				ta.append("[Admin]->[User] " + tf.getText() + "\n");
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
		AdminChatClient client = new AdminChatClient("Admin");

	}
}
