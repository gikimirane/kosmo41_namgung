import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
//콘솔 멀티채팅 클라이언트 프로그램
public class MultiClient2 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("이름을 입력해 주세요.");
		Scanner s = new Scanner(System.in);
		String s_name = s.nextLine();
		
		PrintWriter out =null;
		BufferedReader in = null;
		
		try {
			String ServerIP = "Localhost";
//			args[0] 을 넣으면 클라이언트가 java를 부를때 서버 ip를 넣어줘야 하고..
//			내꺼에 접속할꺼면 localhost 해도 내꺼, 127.0.0.1도 내꺼야, ::1 로 해도 내꺼야 
//			객체 생성할 때 뭘 넣느냐에 따라서 바로 연결이 된다~
			
			Socket socket = new Socket(ServerIP,9999); //소켓 객체 생성
			System.out.println("서버와 연결이 되었습니다.");
			
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader
					(socket.getInputStream()));
			
			out.println(s_name);
			
			while(out != null) {
				try {
//					입력
					if(in !=null) {
						System.out.println("Receive : "+in.readLine());
					}
//					출력
					String s2 = s.nextLine();
					
					if(s2.equals("q") || s2.equals("Q")) {
						out.println(s2);
						break;
					}else {
//						여기서 sysout하면 서버에 안가! 조심해라~
						out.println(s_name+" => "+s2);
					}
					
				}catch(IOException e) {
					System.out.println("예외 : "+e);
				}
			}
			
		}catch(Exception e) {
			System.out.println("예외[MultiClient class]:"+e);
		}
	}
}
