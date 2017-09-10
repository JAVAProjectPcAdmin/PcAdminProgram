package ChattingServer;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class AdminChatClient extends Frame implements ActionListener, MouseListener {
	TextField tf, tf2;
	TextArea ta;
	Button b, b2;
	List list;
	Dialog dia;
	BufferedReader br;
	BufferedWriter bw;
	PopupMenu pm;
	String id;
	MenuItem mi;

	public AdminChatClient(String id) throws Exception {
		super(id + "님의 채팅창입니다");
		this.id = id;
		mi = new MenuItem("메세지");
		pm = new PopupMenu();
		pm.add(mi);

		dia = new Dialog(this);
		tf = new TextField(15);
		ta = new TextArea();
		b = new Button("나가기");
		b2 = new Button("보내기"); // 쪽지 보내기
		list = new List();
		tf2 = new TextField(); // 쪽지보내기 텍필
		list.add(pm);
		Panel p1 = new Panel();
		Panel p2 = new Panel();

		p1.setLayout(new BorderLayout());
		p2.setLayout(new BorderLayout());

		p1.add(ta);
		p1.add(list, "East");

		p2.add(tf);
		p2.add(b, "East");

		add(p1, "Center");
		add(p2, "South");
		list.addMouseListener(this);
		tf.addActionListener(this);
		b.addActionListener(this);
		b2.addActionListener(this);
		mi.addActionListener(this);
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
				case "enter":
					ta.append(array[1] + "님 입장~\n");
					break;

				case "msg":
					ta.append(array[1] + "\n");
					break;

				case "guestlist":
					list.removeAll();

					int len = array.length;
					for (int i = 1; i < len; i++)
						list.add(array[i]);
					break;

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
				sendMsg("msg/" + tf.getText());
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

		else if (e.getSource() == b2) {
			try {
				ta.append("[" + id + "->" + list.getSelectedItem() + "]" + tf2.getText() + "\n");
				sendMsg("귓속말/" + id + "&" + list.getSelectedItem() + "&" + tf2.getText());
				dia.setVisible(false);
				dia.dispose();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == mi) {
			dia.add(tf2);
			dia.add(b2);
			dia.setLayout(new GridLayout(2, 0));
			dia.setBounds(300, 300, 200, 200);
			dia.setVisible(true);
		}

	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 3 && list.getSelectedItem() != null) {

			pm.show(list, e.getX(), e.getY());

		}

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String args[]) throws Exception {
		AdminChatClient client = new AdminChatClient("관리자");

	}
}
