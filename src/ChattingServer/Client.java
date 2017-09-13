package ChattingServer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Client {
	public Client(String who) {
		Socket socket = null;
		BufferedWriter bw = null;
		Scanner s = new Scanner(System.in);
		
		BufferedReader br =null;
		
		ClientFrame f =null;
		try {
		
			socket = new Socket("70.12.115.60", 7788);
			System.out.println("서버랑 연결됬네?!");
			
			f = new ClientFrame(socket,who);
			
			
		} catch (IOException e) {
			System.out.println("연결이 끊어졌습니다.");
		}
	}

}   
//////////////////////////////////////////
  

