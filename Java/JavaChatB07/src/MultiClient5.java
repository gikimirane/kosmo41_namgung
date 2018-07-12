import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MultiClient5 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("이름을 입력하세요.");
		Scanner s = new Scanner(System.in);
		String s_name = s.nextLine();
		
		try {
//			String ServerIP = "localhost";
			String ServerIP = args[0];
			Socket socket = new Socket(ServerIP,9999);
			System.out.println("서버에 연결되었습니다...");
			
			Thread receiver = new Receiver5(socket);
			receiver.start();
			
			Thread sender = new Sender5(socket,s_name);
			sender.start();
		}catch(Exception e) {
			System.out.println("예외 [MultiClient class] : "+e);
		}
	}
}
