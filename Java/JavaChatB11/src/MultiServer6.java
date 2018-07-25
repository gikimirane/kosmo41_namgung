import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiServer6 {
	ServerSocket serverSocket = null;
	Socket socket =null;
	Map<String, PrintWriter> clientMap;
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

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

	public void sendAllMsg(String user,String msg) {
		Iterator<String> it = clientMap.keySet().iterator();
		while(it.hasNext()) {
			try {
				PrintWriter it_out = (PrintWriter)clientMap.get(it.next());
				if(user.equals("")) {
					it_out.println(msg);
				}else {
					it_out.println("["+user+"] "+msg);
				}
				
			}catch(Exception e) {
				System.out.println("예외 : "+e);
			}
		}
		
	}
	public void list(PrintWriter out) {
		Iterator<String> itr = clientMap.keySet().iterator();
		String keys="사용자 리스트 [";
		
		while(itr.hasNext()) {
			keys += (String)itr.next()+",";
		}
		keys = keys.substring(0,keys.length()-1)+"]";
		out.println(keys);
	}
	public void commendInput(PrintWriter out, String s, String name) {
		String str = s; //클라이언트에서 받은 text가 됨
		String com= ""; 

		try {
			com = str.substring(1, str.indexOf(" "));
		}catch(Exception e){
			com = str.substring(1);
		}
		
		if(com.equals("to")) {
			singleChat(str,name);
		}else if(com.equals("list")) {
			list(out);
		}
	}
	public void singleChat(String input, String name) {
		
		String str = input;
		String temp = str.substring(str.indexOf(" ")+1);
		String friendName = temp.substring(0,temp.indexOf(" "));
		String txt = temp.substring(temp.indexOf(" ")+1);
		
//		이건 귓속말 시작한 사람한테 띄워줌
		PrintWriter myAdd = clientMap.get(name);
		myAdd.println(friendName+"님께 귓속말 전송 : "+txt);
		
//		친구한테 메시지 전송
		PrintWriter address = clientMap.get(friendName);
		address.println(name+"님의 귓속말 :"+txt);
	}
	public boolean checkName(String name) {
		int count =0;
		Iterator<String> itr = clientMap.keySet().iterator();
		while(itr.hasNext()) {
			if(itr.next().equals(name)) {
				count++;
			}
		}		
		if(count==0) {
			return true;
		}else {
			return false;
		}
	}

	public static void main(String[] args) throws SQLException {

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
						this.socket.getInputStream(),"utf-8"));
			}catch(Exception e) {
				System.out.println("예외 : "+e);
			}
		}

		public void run() {
			
			Connection con =null;
			try {
				con = DriverManager.getConnection(
						"jdbc:oracle:thin:@ec2-13-125-210-91.ap-northeast-2.compute.amazonaws.com:1521:xe",
						"scott",
						"tiger");
				System.out.println("DB 연결 완료!");
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				System.out.println("Server Run 에서 DB 연결 실패");
			}
			
			PreparedStatement pstmt=null;
			String sql = null;
			
			String name ="";
			try {
				name = in.readLine();
				name = URLDecoder.decode(name,"UTF-8");
				
				sendAllMsg("",name +"님이 입장하셨습니다.");
				clientMap.put(name, out);
				System.out.println("현재 접속자 수는 "+clientMap.size()+"명 입니다.");

				String s ="";
				while(in!=null) {
					s = in.readLine();
					System.out.println("["+name+"] "+ s);
					
					if(s.equals("q")|| s.equals("Q")) {
						break;
					}
					if(s.startsWith("/")) {
						commendInput(out,s,name);
					}
					else sendAllMsg(name,s);
				}
			}catch(Exception e) {
				System.out.println("예외 : "+e);
			}finally {
				clientMap.remove(name);
				sendAllMsg("",name+"님이 퇴장하셨습니다.");
				
				try {
					sql = "delete from emp where name ="+"'"+name+"'";
					pstmt = con.prepareStatement(sql);
					int updateCount = pstmt.executeUpdate();
					con.commit();
					
					System.out.println("delete Count : "+updateCount);
					
					
				}catch(Exception e) {
					System.out.println("여기서 에러인가용?");
					e.printStackTrace();
				}
				
				System.out.println("현재 접속자 수는 "+clientMap.size()+"명 입니다.");
				
				try {
					in.close();
					out.close();
					socket.close();
					if(pstmt!=null) pstmt.close();
					if(pstmt != null)pstmt.close();
					if(con!=null) con.close();
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
