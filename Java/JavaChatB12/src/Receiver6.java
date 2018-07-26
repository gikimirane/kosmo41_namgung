import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URLDecoder;

public class Receiver6 extends Thread {
 	Socket socket;
 	BufferedReader in = null;
 	
 	public Receiver6(Socket socket) {
 		this.socket = socket;
 		try {
 			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(),"utf-8"));
//			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
 		}catch(Exception e) {
 			System.out.println("예외 : "+e);
 		}
 	}
	
	public void run() {
 		while(in!=null) {
 			try {
 				//얘는 한번 읽어오면 끝이라서 날아감!
 				//다시 print할 수 없음
				String text = in.readLine();
 				System.out.println(URLDecoder.decode(text,"UTF-8"));
 			}catch(Exception e) {
 				System.out.println("예외 : "+e);
 			}
 		}
 		try {
 			in.close();
 		}catch(Exception e) {
 			System.out.println("예외3 : "+e);
 		}
 	}
}
