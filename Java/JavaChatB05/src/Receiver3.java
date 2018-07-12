import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receiver3 extends Thread {
	Socket socket;
	BufferedReader in = null;
	
//	생성자 부분, 뭔가 생성할 때 읽어오는 영역(in)을 만듬
	public Receiver3(Socket socket) {
		this.socket = socket;
		
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			
		}catch(Exception e) {
			System.out.println("예외1 : "+e);
		}
	}
	@Override 
//	Thread 클래스의 run 메소드를 재정의 함, Thread가 실행되는 동안 하는 일 
//	읽어 온 부분을 출력해주는 영역
	public void run() {
		while(in!=null) {
			try {
				System.out.println("Thread Receive : "+in.readLine());
				
			}catch(Exception e) {
				System.out.println("예외 2 : "+ e);
			}
		}
		try {
			in.close();
		}catch(Exception e) {
			System.out.println("예외 3 : "+e);
		}
	}
}
