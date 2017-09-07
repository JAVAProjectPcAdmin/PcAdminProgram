package chatServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class adminChatGUI extends JFrame{
	private JTextArea textArea = new JTextArea();
	private JTextField textField = new JTextField();
	private BufferedWriter bw;
	private BufferedReader br;
	private ChatServer server = new ChatServer();

	////////////////////////////////////////////////////////
	public adminChatGUI() {
		// 그래픽 환경 초기화 작업
		textArea.setEditable(false);
		textField.setBackground(Color.PINK);
		ChatListener listener = new ChatListener();
		textField.addActionListener(listener);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);

		setSize(300, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		// 네트워크 초기화 메소드 실행.
	
		// 연결되고 나면

	}
	
	class ChatListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String msg = " : "+textField.getText()+"\n";
			server.sendMessage(msg);
			textField.setText("");
			textArea.append("나:" + msg + "\n");
			
		}
		
	}
	public static void main(String[] args) {
		new adminChatGUI();
	}
}
