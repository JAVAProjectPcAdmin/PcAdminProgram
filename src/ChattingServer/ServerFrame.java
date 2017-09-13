package ChattingServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerFrame extends JFrame {
	// 그래픽 관련 멤버변수
	private JTextArea textArea = new JTextArea();
	private JTextField textField = new JTextField();
	BufferedReader br = null;
	BufferedWriter bw = null;
	Socket socket;
	String who = "";

	////////////////////////////////////////////////////////
	public ServerFrame(Socket socket, String who) {
		// 그래픽 환경 초기화 작업
		this.socket = socket;
		this.who = who;

		textArea.setEditable(false);
		textField.setBackground(Color.PINK);

		textField.addActionListener(new MyListener());

		add(new JScrollPane(textArea), BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);

		setTitle(who);
		setSize(300, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image Iconimg = toolkit.getImage("images\\chatting.png");
		setIconImage(Iconimg);
		
		setVisible(true);

		tt t =new tt(socket, textArea); 
		t.start();
	}

	class MyListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			String writeMsg = textField.getText();
			System.out.println(writeMsg);

			try {
				bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				bw.write(who + ">>" + writeMsg + "\n");
				bw.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			textArea.append(who + ">>" + writeMsg + "\n");
			textField.setText("");
		}
	}

	class tt extends Thread {
		Socket socket;
		JTextArea area;

		public tt(Socket socket, JTextArea textArea) {
			this.socket = socket;
			this.area = textArea;
		}

		@Override
		public void run() {
			String receiveMsg;
				try {
					br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					while (socket.isConnected()) {
						
					receiveMsg = br.readLine();

					textArea.append(receiveMsg + "\n");

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	//////////////////////////////////////////////////////////////////

}