package chatServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import AdminServer.User;
import userUsingState.UserUsingStateGUI;

public class UserChatGUI extends JFrame {
	private JTextArea textArea = new JTextArea();
	private JTextField textField = new JTextField();
	private UserChatClient client = new UserChatClient();
	private static String name;
	User user;

	////////////////////////////////////////////////////////
	public UserChatGUI() {
		// 그래픽 환경 초기화 작업
		textArea.setEditable(false);
		textField.setBackground(Color.PINK);
		ChatListener listener = new ChatListener();
		textField.addActionListener(listener);;
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
		setLocation(500, 300);
		setSize(300, 400);
		setVisible(true);

		client.setGUI(this);
		client.setName(name);
		client.connect();

	}

	class ChatListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String msg = name+":" + textField.getText() + "\n";
			client.sendMessage(msg);
			textField.setText("");

		}

	}

	public void appendMsg(String msg) {
		textArea.append(msg);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("이름 입력 : ");
		name = scanner.nextLine();
		scanner.close();	
		
		new UserChatGUI();
		
	}
}
