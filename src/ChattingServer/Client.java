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
		
		
		ChattingFrame f =null;
		try {
		
			socket = new Socket("70.12.115.53", 7788);
			System.out.println("������ ������?!");
			
			f = new ChattingFrame(socket,who);
			
			
		} catch (IOException e) {
			System.out.println("������ ���������ϴ�.");
		}
	}

}   
//////////////////////////////////////////
  

