import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer2 {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		String s ="";
		
		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("서버가 시작되었습니다.");
			
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress()+" : "+socket.getPort());
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
//			통신이 끊어지면 in이 null 됨
			while(in!=null) {
				s = in.readLine();
//				s가 null 이라는것 역시 끊어진다는 말인데 2중 체크를 한것임
				if(s==null) break;
				if(s.equals("q") || s.equals("Q")) break;
//				들어온 내용을 서버측에서도 볼 수 있게 print 해주고~
				System.out.println(s);
//				서버에서 내보내겠다 그러면 클라이언트가 볼 수 있겠지
				out.println("> " + s);
			}
			System.out.println("Bye..");
		}catch(Exception e) {
			System.out.println("예외 1 : "+e);
//			e.printStackTrace();
		}finally {
			try {
				in.close();
				out.close();
				
				socket.close();
				serverSocket.close();
			}catch(Exception e) {
				System.out.println("예외 2 : "+e);
//				e.printStackTrace();
			}
		}
	}
}
//C:\Windows\System32\drivers\etc 여기에서 hosts 파일 열어서 나의 ip주소 넣고 별명 넣으면
//localhost 처럼 keyword처럼 쓸 수 있음!