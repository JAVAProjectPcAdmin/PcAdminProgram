package ChattingServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread{
	BufferedReader br =null;
	Socket socket;
	String who ="»ó´ë¹æ";
	
	public ReadThread(Socket socket) {
		this.socket=socket;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
		br= new BufferedReader(new InputStreamReader(socket.getInputStream()));
	
			String receiveMsg;
			while(true) {
			receiveMsg = br.readLine();
			System.out.println(who+">>>"+receiveMsg);
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
