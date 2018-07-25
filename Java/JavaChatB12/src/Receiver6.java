import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receiver6 extends Thread {
	Socket socket;
	BufferedReader in = null;
	
	public Receiver6(Socket socket) {
		this.socket = socket;
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(),"utf-8"));
		}catch(Exception e) {
			System.out.println("예외 : "+e);
		}
	}
	
	public void run() {
		while(in!=null) {
			try {
				System.out.println(in.readLine());
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
