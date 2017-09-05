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
	// �׷��� ���� �������
	private JTextArea textArea = new JTextArea();
	private JTextField textField = new JTextField();
	private BufferedWriter bw;
	private BufferedReader br;

	////////////////////////////////////////////////////////
	public TalkGUI() {
		// �׷��� ȯ�� �ʱ�ȭ �۾�
		textArea.setEditable(false);
		textField.setBackground(Color.PINK);

		textField.addActionListener(new MyListener());

		add(new JScrollPane(textArea), BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
		setLocation(500, 300);
		setSize(300, 400);

		setVisible(true);
		// ��Ʈ��ũ �ʱ�ȭ �޼ҵ� ����.
		server();
		// ����ǰ� ����
		new ReviveThread().start();

	}

	public void server() {

		try {
			ServerSocket serverSocket = new ServerSocket(2222);
			Socket socket = new Socket("70.12.115.60", 2222);

			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// ����ǰ� ���� �޼��� ���� ������ ���۽�Ű��
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

			textArea.append("��:" + writeMsg + "\n");
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
					textArea.append("Ŭ���̾�Ʈ:" + receiveMsg + "\n");
				}
			} catch (IOException e) {
				textArea.append("Ŭ���̾�Ʈ ����\n");
				e.printStackTrace();
			}
		}

	}

}
