package chatServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
	User user;

	////////////////////////////////////////////////////////
	public UserChatGUI() {
		// 그래픽 환경 초기화 작업
		textArea.setEditable(false);
		textField.setBackground(Color.PINK);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
		setLocation(500, 300);
		setSize(300, 400);
		setVisible(true);
		
		client.setGUI(this);
//		client.setName(user.getName());
		client.connect();

	}
	public void actionPerformed(ActionEvent e) {
		String msg = " : "+textField.getText()+"\n";
		client.sendMessage(msg);
		textField.setText("");
		textArea.append("나:" + msg + "\n");
	}
	public static void main(String[] args) {
		new UserChatGUI();
	}
}
