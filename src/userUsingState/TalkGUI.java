package userUsingState;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TalkGUI extends JFrame {
	// 그래픽 관련 멤버변수
	private JTextArea textArea = new JTextArea();
	private JTextField textField = new JTextField();
	private BufferedWriter bw;
	private BufferedReader br;

	////////////////////////////////////////////////////////
	public TalkGUI() {
		// 그래픽 환경 초기화 작업
		textArea.setEditable(false);
		textField.setBackground(Color.PINK);

		textField.addActionListener(new MyListener());

		add(new JScrollPane(textArea), BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
		setLocation(500, 300);
		setSize(300, 400);

		setVisible(true);
		// 네트워크 초기화 메소드 실행.
		server();
		// 연결되고 나면
		new ReviveThread().start();

	}

	public void server() {

		try {
			ServerSocket serverSocket = new ServerSocket(2222);
			Socket socket = new Socket("70.12.115.60", 2222);

			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// 연결되고 나면 메세지 수신 쓰레드 시작시키기
			// new ReveiveThread().start();
			// new ReveiveThread().start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	class MyListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String writeMsg = textField.getText();

			textArea.append("나:" + writeMsg + "\n");
			textField.setText("");

			try {
				if (bw != null) {
					bw.write(writeMsg + "\n");
					bw.flush();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block

				e1.printStackTrace();
			}

		}
	}

	class ReviveThread extends Thread {
		@Override
		public void run() {
			try {
				while (true) {
					String receiveMsg = br.readLine();
					textArea.append("클라이언트:" + receiveMsg + "\n");
				}
			} catch (IOException e) {
				textArea.append("클라이언트 퇴장\n");
				e.printStackTrace();
			}
		}

	}

}
