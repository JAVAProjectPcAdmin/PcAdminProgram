package ChattingServer;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JTextField;

public class WriteThread extends Thread{
	BufferedWriter bw=null;
	Socket socket;
	String who ="³ª";
	JTextField textfield;
	
	public WriteThread(Socket socket,JTextField textfield) {
		this.socket=socket;
		this.textfield=textfield;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		Scanner s= new Scanner(System.in);
		try {
		bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		
		

		while(true) {
			System.out.print(who+">>");
			String sendMsg = s.nextLine();
			
			bw.write(sendMsg+"\n");
			bw.flush();
			textfield.setText(sendMsg);
			
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
