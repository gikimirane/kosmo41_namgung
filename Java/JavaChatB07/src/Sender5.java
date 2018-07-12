import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Sender5 extends Thread{
	Socket socket;
	PrintWriter out = null;
	String name;
	
//	생성자 부분, 뭔가 생성할 때 내보내는 영역(out)을 만듬
	public Sender5(Socket socket, String name) {
		this.socket = socket;
		try {
			out= new PrintWriter(this.socket.getOutputStream(),true);
			this.name=name;
		}catch(Exception e) {
			System.out.println("예외S3 : "+e);
		}
	}
	
	public void run() {
		Scanner s = new Scanner(System.in);
		try {
//			이 값을 아웃한테 print해줘
			out.println(name);
			while(out!=null) {
				try {
					String s2 = s.nextLine();
					if(s2.equals("q")||s2.equals("Q")) {
						out.println(s2);
						break;
					}					
					if(s2.equals("/list")) {
						out.println(s2);
					}
					else {
						out.println(name+" => "+s2);
					}
					
				}catch(Exception e) {
					System.out.println("예외S1 : "+e);
				}
			}
			out.close();
			socket.close();
		}catch(Exception e) {
			System.out.println("예외S2 : "+e);
		}
	}
}
