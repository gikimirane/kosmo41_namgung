import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import java.util.Set;
//금칙어 메소드 만들어서 sendallmsg랑 귓속말 호출하기 전에 메소드 호출시킴
public class MultiServer6 {
	ServerSocket serverSocket = null;
	Socket socket =null;
	Map<String, PrintWriter> clientMap;
	Map<String, PrintWriter> blockClient;
	static Connection con=null;
	
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
	//현재 대기실에 있는 사용자들에게 메시지를 뿌려주는 영역
	public void sendAllMsg(String user,String msg) throws SQLException, UnsupportedEncodingException{
		
		Iterator<String> it = clientMap.keySet().iterator();
		PreparedStatement pstmt=null;
		String sql=null;
		ResultSet rs=null;
		PrintWriter it_out=null;
 		while(it.hasNext()) {
 			try {
 				String bName = it.next();
 				it_out = (PrintWriter)clientMap.get(bName);
 				int count = 0;
 				//양 방향으로 차단시킴
 				sql = "select count(*) from block where oname = '"+bName+"' and bname = '"+user+"'";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) count = rs.getInt(1);
				if(count>0) {
					continue;
				}
				rs.close();
				
				pstmt.clearParameters();
				sql = "select count(*) from block where oname = '"+user+"' and bname = '"+bName+"'";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				pstmt.clearParameters();
				while(rs.next()) count = rs.getInt(1);
				if(count>0) {
					continue;
				}
								
				if(user.equals(""))	it_out.println(URLDecoder.decode(badWordCheck(msg),"UTF-8"));
				else				it_out.println(URLDecoder.decode("["+user+"] "+badWordCheck(msg),"UTF-8"));
			
 			}catch(Exception e) {
 				
 			}finally {
 				if(rs!=null) rs.close();
 				if(pstmt!=null) pstmt.close();
 			}
 		}
	}
	// '/'로 시작할 경우 명령어라고 생각하고 메소드를 호출함, 이 영역 내에서 각각 메소드를 호출시킴
	public void commendInput(PrintWriter out, String s, String name) throws SQLException {
		String str = s; //클라이언트에서 받은 text가 됨
		String com= ""; 

		try {
			com = str.substring(1, str.indexOf(" "));
		}catch(Exception e){
			com = str.substring(1);
		}
		
		if(com.equalsIgnoreCase("to")) {
			setWhisper(str,name);
		}else if(com.equalsIgnoreCase("list")) {
			allList(out);
		}else if(com.equalsIgnoreCase("setblack")) {
			setBlack(str,name,out);
		}else if(com.equalsIgnoreCase("unsetblack")) {
			unsetBlacklist(str,name,out);
		}else if(com.equalsIgnoreCase("setblock")) {
			setBlock(str,name,out);
		}else if(com.equalsIgnoreCase("setbadword")) {
			setBadWord(str,name,out);
		}else if(com.equalsIgnoreCase("unsetBlock")) {
			unsetBlock(str,name,out);
		}
	}
	//현재 대기실의 사용자 list를 요청할 경우 list를 뿌려줌
	public void allList(PrintWriter out) {
		Iterator<String> itr = clientMap.keySet().iterator();
		String keys="사용자 리스트 [";
		
		while(itr.hasNext()) {
			keys += (String)itr.next()+",";
		}
		keys = keys.substring(0,keys.length()-1)+"]";
		out.println(keys);
	}
	//나쁜말인지 체크하는 메소드, sendAllmsg와 whisper에서 확인함
	public String badWordCheck(String str) throws SQLException {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		con = DriverManager.getConnection(
				"jdbc:oracle:thin:@ec2-13-125-210-91.ap-northeast-2.compute.amazonaws.com:1521:xe",
				"scott",
				"tiger");

		String sql = "select * from offen_lang";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery(sql);
		con.commit();
		
		ArrayList<String> arr = new ArrayList<String>();
		
		while(rs.next()) {
			 arr.add(rs.getString(1));
		}
		for(String a : arr) {
			if(str.contains(a)) {

				str = str.replace(a, "*나쁜말*");
				return str;
			}
		}
		try {
			if(rs!=null) rs.close();
			if(pstmt !=null) pstmt.close();
			if(con!=null) con.close();
		}catch(SQLException sqle) {}
		return str;
	}
	//사용자가 나쁜말을 직접 등록할 수 있는 메소드, /setbadword 나쁜말 을 입력 시 동작함
	public void setBadWord (String str1, String name1, PrintWriter out) throws SQLException {
		String str = str1;
		String name = name1;
		String temp = str.substring(str.indexOf(" "));
		String word = temp.substring(1,temp.length());
		
		try{
			sqlCall("insert into offen_lang values ('"+word+"')");
			System.out.println(name+"님이 "+word+"를 나쁜말 목록에 추가했습니다.");
			out.println(name+"님이 "+word+"를 나쁜말 목록에 추가했습니다.");
		}catch(Exception e) {
			out.println("이미 등록된 나쁜말 입니다.");
		}
	}
	//사용자가 친구를 차단하는 영역
	public void setBlock(String str1, String name1, PrintWriter out) throws SQLException {
		String str = str1;
		String name = name1;
		String temp = str.substring(str.indexOf(" "));
		String friendName = temp.substring(1,temp.length());
		sqlCall("insert into block values ('"+name+"','"+friendName+"')");
		System.out.println(name+"님이 "+friendName+"님을 차단목록에 추가했습니다.");
		out.println(name+"님이 "+friendName+"님을 차단목록에 추가했습니다.");
	}
	public void unsetBlock(String str1, String name1, PrintWriter out) throws SQLException {
		String str = str1;
		String name = name1;
		String temp = str.substring(str.indexOf(" "));
		String friendName = temp.substring(1,temp.length());
		int count=0;
		System.out.println("지워줘 block");
		
		count = updateCount("delete from block where oname = '"+name+"' and bname ='"+friendName+"'");
		if(count > 0) {
			System.out.println(name+"님이 등록했던 "+friendName+"님을 차단 해제 합니다.");
			out.println(name+"님이 등록했던 "+friendName+"님을 차단 해제 합니다.");
		}else {
			System.out.println(name+"님이 차단한 사람이 아닙니다.");
			out.println(name+"님이 차단한 사람이 아닙니다.");
		}
		
	}
	//블랙리스트 등록메소드
	public void setBlack (String str1,String name1, PrintWriter out) throws SQLException {
		String str = str1;
		String name = name1;
		String temp = str.substring(str.indexOf(" "));
		String friendName = temp.substring(1,temp.length());
		sqlCall("insert into blacklist values ('"+friendName+"','"+name+"')");
		System.out.println(name+"님이 "+friendName+"님을 Blacklist에 추가했습니다.");
		out.println(name+"님이 "+friendName+"님을 Blacklist에 추가했습니다.");
	}
	//블랙리스트 등록한 사람만이 블랙리스트를 해제할 수 있음
	public void unsetBlacklist(String str1,String name1,PrintWriter out) throws SQLException {
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
	//귓속말 메소드, 서버에서 /to name 메세지를 붙혀서 올려주거나 사용자가 그렇게 입력하면 동작 됨
	public void setWhisper(String input, String name) throws SQLException {

			String str = input;
			String temp = str.substring(str.indexOf(" ")+1);
			String friendName = temp.substring(0,temp.indexOf(" "));
			String txt = temp.substring(temp.indexOf(" ")+1);
			
			
//			이건 귓속말 시작한 사람한테 띄워줌
			PrintWriter myAdd = clientMap.get(name);
			myAdd.println(friendName+"님께 귓속말 전송 : "+badWordCheck(txt));
			
//			친구한테 메시지 전송
			PrintWriter address = clientMap.get(friendName);
			address.println(name+"님의 귓속말 :"+badWordCheck(txt));
		}
	
	//-----------DB접근 메소드 영역---------
	//SQL 쿼리문 전송할 때 매번 필요한 내용들을 담아 메소드로 정의 함
	public static void sqlCall(String iSql) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt=null;
		
		try {
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@ec2-13-125-210-91.ap-northeast-2.compute.amazonaws.com:1521:xe",
					"scott",
					"tiger");
			
			String sql = iSql;
			pstmt = con.prepareStatement(sql);
			int updateCount=pstmt.executeUpdate();
			con.commit();
			System.out.println("update Count : "+updateCount);
			
		}catch(Exception e) {
			System.out.println("쿼리 호출 시 Error "+e);
		}finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException sqle) {}
		}
	}
	//updateCount 반환하는 메소드
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
		}finally {
			try {
				if(pstmt !=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException sqle) {}
		}
	}
	
	
	public static void main(String[] args) throws SQLException {

		MultiServer6 ms = new MultiServer6();
		ms.init();
		con.close();
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
					else {
						sendAllMsg(name,s);
					}
				}
				
			}catch(Exception e) {
				System.out.println("예외 1 : "+e);
				//이게 원래 잡히는지도 확인해봐야 함...ㅜ.ㅜ
			}finally {
				try {
					sendAllMsg("",name+"님이 퇴장하셨습니다.");
					clientMap.remove(name);
					System.out.println("현재 접속자 수는 "+clientMap.size()+"명 입니다.");
					
					
					String sql = "";
					
					int updateCount=0;

					PreparedStatement pstmt;
					sql = "delete from block where oname ="+"'"+name+"'";
					pstmt = con.prepareStatement(sql);
					updateCount = pstmt.executeUpdate();
					System.out.println("Server block delete Count : "+updateCount);
					pstmt.clearParameters();
					
					sql = "delete from emp where name ="+"'"+name+"'";
					pstmt = con.prepareStatement(sql);
					updateCount = pstmt.executeUpdate();
					con.commit();
					System.out.println("Server emp delete Count : "+updateCount);
					pstmt.clearParameters(); //pstmt close안하고 또 쓸수 있는 메소드!
					
					
					sql = "delete from block where oname ="+"'"+name+"'";
					pstmt = con.prepareStatement(sql);
					updateCount = pstmt.executeUpdate();
					con.commit();
					System.out.println("Server block delete Count : "+updateCount);
					pstmt.close();
					
				}catch(Exception e) {
					System.out.println("여기서 에러인가용?");
					e.printStackTrace();
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
		}
	}
}
