import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer4 {

	ServerSocket serverSocket = null;
	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	String s = "";

//	기본 생성자
	public MultiServer4() {
	}
	public void init() {
		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("서버가 시작되었습니다.");
			
			socket = serverSocket.accept();   
			System.out.println(socket.getInetAddress()+" : "+socket.getPort());
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
//			통신이 끊어지기 전까지.. in이 null이라는 것은 끊어졌다는 
			while(in!=null) {
				s = in.readLine();  //in으로 읽어온 내용을 한줄씩 s에 넣어서 찍어줘랑
				if(s==null) break;  //s가 비어있으면 멈춰
				if(s.equals("q") || s.equals("Q")) break;		
				System.out.println(s);  //서버에서 들어온 내용을 찍어주고~
//				out.println("> " + s);
//				메시지 찍어주는 메소드로 대체하여 처리, 접속된 모든 클라이언트들에게 메시지 전달
				sendAllMsg(s);      //들어온 메시지를 다시 클라이언트로 보내(out에게 ㄱㄱ)
			}
			System.out.println("Bye...");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
				out.close();
				
				socket.close();
				serverSocket.close();
			}catch(Exception e) {
//				System.out.println("예외 2 : "+e);
				e.printStackTrace();
			}
		}
	}
//	접속된 모든 클라이언트들에게 메시지 전송
	public void sendAllMsg(String msg) {
		try {
			out.println(msg);
			
		}catch(Exception e) {
			System.out.println("예외 : "+e);
		}
	}
	
	public static void main(String[] args) {
//		이렇게 main이 포함된 클래스에서 메인메소드의 객체를 만들면 static이 없어도 됨
		MultiServer4 ms = new MultiServer4();
		ms.init();
	}
}
