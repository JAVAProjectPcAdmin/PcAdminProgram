package ChattingServer;
import java.io.IOException;
import java.net.Socket;


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
  

