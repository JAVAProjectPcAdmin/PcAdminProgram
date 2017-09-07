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

public class AdminChatGUI extends JFrame{
	private JTextArea textArea = new JTextArea();
	private JTextField textField = new JTextField();
	private ChatServer server = new ChatServer();

	////////////////////////////////////////////////////////
	public AdminChatGUI() {
		
		textArea.setEditable(false);
		textField.setBackground(Color.PINK);
		ChatListener listener = new ChatListener();
		textField.addActionListener(listener);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
		setSize(300, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
		server.setGui(this);
//		server.ServerSetting();
	

	}
	
	class ChatListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String msg = "¼­¹ö : "+textField.getText()+"\n";
			server.sendMessage(msg);
			textField.setText("");
			
		}
		
	}
	public void appendMsg(String msg) {
		textArea.append(msg);
	}
	public static void main(String[] args) {
		new AdminChatGUI();
	}
}
