import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer5 {

	ServerSocket serverSocket = null;
	Socket socket =null;
	
	public MultiServer5() {
//		생성자
	}
	
	public void init() {
		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("서버가 시작되었습니다.");
			
			while(true) {
//				나한테 들어오는 애들 기다렸다가 쓰레드 msr 1개씩 생성해서 돌려버려~
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress()+" : "+socket.getPort());
				
				Thread msr = new MultiServerT(socket);
				msr.start();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				serverSocket.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
//		서버 객체 생성
		MultiServer5 ms = new MultiServer5();
		ms.init();
	}
//	내부 클래스
//	클라이언트로부터 읽어온 메시지를 다른 클라이언트에게 보내는 역할하는 메서드
	class MultiServerT extends Thread {
		Socket socket;
		PrintWriter out =null;
		BufferedReader in = null;
		
		public MultiServerT(Socket socket) {
			this.socket=socket;
			try {
				out = new PrintWriter(this.socket.getOutputStream(),true);
				in = new BufferedReader(new InputStreamReader(
						this.socket.getInputStream()));
			}catch(Exception e) {
				System.out.println("예외 : "+e);
			}
		}
		
//		쓰레드 사용을 위한 run 메서드 재정의
		public void run() {
			String s = "";
			try {
				while(in!=null) {
					s = in.readLine();
					
					if(s==null) break;
					if(s.equals("q")||s.equals("Q")) break;
					
					System.out.println(s);
					sendAllMsg(s,out);
				}
			}catch(Exception e) {
				System.out.println("예외 : "+e);
			}finally {
				try {
					in.close();
					out.close();
					socket.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		public void sendAllMsg (String msg, PrintWriter out) {
			try {
//				out.(들어와있는 모든 애들 봐라)
				out.println(msg);
			}catch(Exception e) {
				System.out.println("예외 : "+e);
			}
		}
	}
}
