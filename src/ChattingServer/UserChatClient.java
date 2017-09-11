package ChattingServer;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class UserChatClient extends Frame implements ActionListener {
	TextField tf, tf2;
	TextArea ta;
	Button b, b2;
	List list;
	BufferedReader br;
	BufferedWriter bw;
	PopupMenu pm;
	String id;
	MenuItem mi;

	public UserChatClient(String id) throws Exception {
		super(id + "���� ä��â�Դϴ�");
		this.id = id;

		tf = new TextField(15);
		ta = new TextArea();
		b = new Button("������");
		list = new List();
		tf2 = new TextField(); // ���������� ����
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
				case "msg":
					ta.append("[��ü]"+array[1] + "\n");
					break;
				case "�ӼӸ�":
					String arr[] = array[1].split("&");
					ta.append("[" + arr[0] + "->" + arr[1] + "]" + arr[2] + "\n");
					break;

				}
			}
		} catch (Exception e) {
			System.out.println("�дٰ�������~");
		}
	}

	public void sendMsg(String msg) throws Exception {
		bw.write(msg + "\n");
		bw.flush();
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == tf) {
			try {
				sendMsg("�ӼӸ�/" + "User2" + "&" + "������2" + "&" + tf.getText());
				ta.append("[User2]->[������] " + tf.getText() + "\n");
				tf.setText("");

			} catch (Exception ee) {
				System.out.println("�����ٰ�������~");
			}
		} else if (e.getActionCommand().equals("������")) {
			try {
				System.exit(0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public static void main(String args[]) throws Exception {
		UserChatClient client = new UserChatClient("User2");

	}
}
