import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiServer6 {

	ServerSocket serverSocket = null;
	Socket socket =null;
	Map<String, PrintWriter> clientMap;
	
	public MultiServer6() {
		clientMap = new HashMap<String, PrintWriter>();
		Collections.synchronizedMap(clientMap);
	}
	
	public void init() {
		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("서버가 시작되었습니다.");
			
			while(true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress()+" : "+socket.getPort());
				
				Thread mst = new MultiServerT(socket);
				mst.start();
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

	public void sendAllMsg(String msg) {
		Iterator it = clientMap.keySet().iterator();
		while(it.hasNext()) {
			try {
				PrintWriter it_out = (PrintWriter)clientMap.get(it.next());
				it_out.println(msg);
			}catch(Exception e) {
				System.out.println("예외 : "+e);
			}
		}
	}
	public void list(PrintWriter out) {
		
		Iterator<String> itr = clientMap.keySet().iterator();
		String keys="";
		while(itr.hasNext()) {
			keys += (String)itr.next()+"\t";
		}
		out.println(keys);
	}
	
	public void singleChat(String name, PrintWriter out) {
//		아놔 모르겄따~~
	}
//	해시맵에서 키값올라와찌
//	out을 찾아서… HashMap에 보면은 요렇게 생겼으니까 =>>  <User이름,키값(주소)> 두번째 키값에 해당되는 애들한테만 메시지를 쏴 줘, 
//	이름을 찾아서 그 이름에 해당되는 주소에만 쏴줘~그게 귓속말이야~~
//	첫글짜가 슬래시인지를 봐 
//	그다음에 공백까지가 명령어인데 to면 귓속말 OK! 두번째 공백~세번째 공백사이가 User이름이자나~ subString 으로 다 쪼개야돼~~
	
	public static void main(String[] args) {
		MultiServer6 ms = new MultiServer6();
		ms.init();
	}
	
	class MultiServerT extends Thread {
		Socket socket;
		PrintWriter out = null;
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
		
		public void run() {
			String name ="";
			try {
				name = in.readLine();
				
				sendAllMsg(name +"님이 입장하셨습니다.");
				clientMap.put(name, out);
				System.out.println("현재 접속자 수는 "+clientMap.size()+"명 입니다.");
				
				String s ="";
				while(in!=null) {
					s = in.readLine();
					System.out.println(s);
										
					if(s.equals("q")|| s.equals("Q")) {
						break;
					}
					if(s.equals("/list")) {
						list(out);	
					}
					else sendAllMsg(s);
				}
			}catch(Exception e) {
				System.out.println("예외 : "+e);
			}finally {
				clientMap.remove(name);
				sendAllMsg(name+"님이 퇴장하셨습니다.");
				System.out.println("현재 접속자 수는 "+clientMap.size()+"명 입니다.");
				
				try {
					in.close();
					out.close();
					
					socket.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
