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
			serverSocket= new ServerSocket(7788);
			while(true) {
				System.out.println("기다리는중....");
			socket= serverSocket.accept(); //기다림  - 연결되면 socket에 들어감 
			System.out.println("클라이언트 요청 들어옴 ");
			new ServerFrame(socket,who);
			}
		} catch (IOException e) {
			System.out.println("연결이 끊어졌습니다.");
		}
		
	}
}
