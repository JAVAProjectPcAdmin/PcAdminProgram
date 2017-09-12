package ChattingServer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) {
		ServerSocket serverSocket =null;
		Socket socket=null;
		String who="Admin";
		
		BufferedWriter bw = null;
		Scanner s = new Scanner(System.in);
		
		BufferedReader br =null;
		
		try {
			serverSocket= new ServerSocket(7777);
			while(true) {
				System.out.println("기다리는중....");
			socket= serverSocket.accept(); //기다림  - 연결되면 socket에 들어감 
			System.out.println("클라이언트 요청 들어옴 ");
			ClientFrame c= new ClientFrame(socket,who);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
