import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MultiClient4 {

	public static void main(String[] args) {
		System.out.println("이름을 입력해 주세요.");
		Scanner s = new Scanner(System.in);
		String s_name = s.nextLine();
		
		PrintWriter out = null;
		
		try {
			String ServerIP = "localhost";
			Socket socket = new Socket(ServerIP,9999);
			System.out.println("서버와 연결이 되었습니다.");
			
			Thread receiver = new Receiver4(socket);
			receiver.start();
			
			
			out = new PrintWriter(socket.getOutputStream(),true);
			
//			out이라는 인스턴스에 입력받은 내용을 println을 이용해 내보내준다
			out.println(s_name);

//			서버로 내보내겠다. out.println을 통해?			
			while(out!=null) {
				try {
					String s2 = s.nextLine();
					if(s2.equals("q") || s2.equals("Q")) {
//						요거를 서버에 보내겠다~
						out.println(s2);
						break;
						
					}else {
//						요렇게 서버로 보내겠다~
						out.println(s_name+" => "+s2);
					}
					
				}catch(Exception e) {
					System.out.println("예외 : "+e);
				}
			}
			
			out.close();
			socket.close();
		}catch(Exception e) {
			System.out.println("예외 [MultiClient class] : "+e);
		}
	}
}
