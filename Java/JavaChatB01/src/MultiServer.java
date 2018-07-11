import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket =null;
		PrintWriter out = null;
		BufferedReader in =null;
		String s ="";
		
		try {
//			port 번호 임의 지정함. 만만한 9999로
			serverSocket = new ServerSocket(9999);
			System.out.println("서버가 시작되었습니다.");
//			누구 들어올때까지 기다림.. scanner 처럼
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() +" : "+socket.getPort());
			
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(
										socket.getInputStream()));
			s = in.readLine();
			System.out.println(s);
			out.println(s);
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
				e.printStackTrace();
			}
		}
	}
}

//telnet 192.168.0.112 9999
//telnet ip주소 포트번호 입력하기