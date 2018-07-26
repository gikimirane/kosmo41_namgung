import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
//금칙어 메소드 만들어서 sendallmsg랑 귓속말 호출하기 전에 메소드 호출시킴
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
	public String badword(String msg) {
		
		//contains로 문자열과 문구를 비교, count를 이용해 0이 아니면 
		//*로 치환해서 msg를 return 해야 함
		//문자열을 찾고, 잘라서 그 개수만큼 *로 치환하기.
		return msg;
	}
	public void sendAllMsg(String user,String msg){
				
		Iterator<String> it = clientMap.keySet().iterator();
 		while(it.hasNext()) {
 			try {
 				PrintWriter it_out = (PrintWriter)clientMap.get(it.next());
 			
 				if(user.equals("")) {
 					it_out.println(URLDecoder.decode(msg,"UTF-8"));
 				}else {
 					it_out.println(URLDecoder.decode("["+user+"] "+msg,"UTF-8"));
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
	public void commendInput(PrintWriter out, String s, String name) throws SQLException {
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
		}else if(com.equals("setblack")) {
			setBlack(str,name,out);
		}else if(com.equals("unsetblack")) {
			unSetBlacklist(str,name,out);
		}else if(com.equals("setblock")) {
			setblock(str,name,out);
		}else if(com.equals("badword")) {
			badword(str,name,out);
		}
	}
	
	public void badword (String str1, String name1, PrintWriter out) throws SQLException {
		String str = str1;
		String name = name1;
		String temp = str.substring(str.indexOf(" "));
		String word = temp.substring(1,temp.length());
		sqlCall("insert into offen_lang values ('"+word+"')");
		System.out.println(name+"님이 "+word+"를 나쁜말 목록에 추가했습니다.");
		out.println(name+"님이 "+word+"를 나쁜말 목록에 추가했습니다.");
	}
	
	public void setblock(String str1, String name1, PrintWriter out) throws SQLException {
		String str = str1;
		String name = name1;
		String temp = str.substring(str.indexOf(" "));
		String friendName = temp.substring(1,temp.length());
		sqlCall("insert into block values ('"+name+"','"+friendName+"')");
		System.out.println(name+"님이 "+friendName+"님을 차단목록에 추가했습니다.");
		out.println(name+"님이 "+friendName+"님을 차단목록에 추가했습니다.");
	}
	//SQL 쿼리문 전송할 때 매번 필요한 내용들을 담아 메소드로 정의 함
	public static void sqlCall(String iSql) throws SQLException {
		Connection con;
		con = DriverManager.getConnection(
				"jdbc:oracle:thin:@ec2-13-125-210-91.ap-northeast-2.compute.amazonaws.com:1521:xe",
				"scott",
				"tiger");
		PreparedStatement pstmt=null;
		String sql = iSql;
		pstmt = con.prepareStatement(sql);
		int updateCount=pstmt.executeUpdate();
		con.commit();
		System.out.println("update Count : "+updateCount);
	}
	
	public static int updateCount(String iSql) throws SQLException {
		Connection con;
		int updateCount=0;
		con = DriverManager.getConnection(
				"jdbc:oracle:thin:@ec2-13-125-210-91.ap-northeast-2.compute.amazonaws.com:1521:xe",
				"scott",
				"tiger");
		PreparedStatement pstmt=null;
		String sql = iSql;
		try {
			pstmt = con.prepareStatement(sql);
			updateCount=pstmt.executeUpdate();
			con.commit();			
			System.out.println("update Count : "+updateCount);
			return updateCount;
		}catch(Exception e) {
			System.out.println("update Count 가 없습니다.");
			return updateCount;
		}
	}

	
	public void setBlack (String str1,String name1, PrintWriter out) throws SQLException {
		String str = str1;
		String name = name1;
		String temp = str.substring(str.indexOf(" "));
		String friendName = temp.substring(1,temp.length());
		sqlCall("insert into blacklist values ('"+friendName+"','"+name+"')");
		System.out.println(name+"님이 "+friendName+"님을 Blacklist에 추가했습니다.");
		out.println(name+"님이 "+friendName+"님을 Blacklist에 추가했습니다.");
	}
		
	public void unSetBlacklist(String str1,String name1,PrintWriter out) throws SQLException {
		String str = str1;
		String name = name1;
		String temp = str.substring(str.indexOf(" "));
		String friendName = temp.substring(1,temp.length());
		int count= updateCount("select name from blacklist where owner='"+name+"' and name = '"+friendName+"'");

		if(count>0) {
			try {
				sqlCall("delete from blacklist where name = '"+friendName+"'");
				System.out.println(name+"님이 설정한 "+friendName+"님을 Blacklist에서 제거 했습니다.");
				out.println(name+"님이 설정한 "+friendName+"님을 Blacklist에서 제거 했습니다.");
			}catch(Exception e) {
				System.out.println("제거 중 error"+e);
			}
		}else {
			System.out.println("등록한 Blacklist가 없습니다.");
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
						this.socket.getInputStream(),"UTF-8"));
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
				sendAllMsg("","현재 접속자 수는 "+clientMap.size()+"명 입니다.");
				
				String s ="";
				
				while(in!=null) {
					s = in.readLine();
					s = URLDecoder.decode(s, "UTF-8");
					
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
				System.out.println("예외 1 : "+e);
				//이게 원래 잡히는지도 확인해봐야 함...ㅜ.ㅜ
			}finally {
	
				try {
					clientMap.remove(name);
					sendAllMsg("",name+"님이 퇴장하셨습니다.");
					System.out.println("현재 접속자 수는 "+clientMap.size()+"명 입니다.");
					
					sql = "delete from emp where name ="+"'"+name+"'";
					pstmt = con.prepareStatement(sql);
					int updateCount = pstmt.executeUpdate();
					con.commit();
					
					System.out.println("Server delete Count : "+updateCount);
					
				}catch(Exception e) {
					System.out.println("여기서 에러인가용?");
					e.printStackTrace();
				}

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
