import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiServer6 {
	ServerSocket serverSocket = null;
	Socket socket =null;
	Map<String, PrintWriter> clientMap;

	static Connection con;
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
	public void init()  {
		
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
	public void createRoom(String user,String str,PrintWriter out) throws SQLException {
		String sql ="";
		String[] str1 = str.split(" ");
		sql = "select count(*) from room where room_owner = '"+user+"'";
		Connection con = ConnectionPool.getConnection("성공");
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery(sql);
		int count=0;
		while(rs.next()) {
			count = rs.getInt("count(*)");
		}
		if(count>0) {
			out.println("본인이 방장인 방이 이미 있습니다.");
			return;
		}
		
		try {
			if (str1[1].equalsIgnoreCase("lock")) {
				sqlCall("insert into room values (rno.nextVal,'5','n','"+str1[2]+"','"+user+"')");
				sqlCall("update emp set room =rno.CURRVAL where name='"+user+"'");
				System.out.println("비밀방이 만들어 졌습니다.");
				out.println("비밀방이 만들어 졌습니다.");
			}
			else if(str1[1].equalsIgnoreCase("unlock")){
				sqlCall("insert into room values (rno.nextVal,'5','y',' ','"+user+"')");
				sqlCall("update emp set room =rno.CURRVAL where name='"+user+"'");
				System.out.println("오픈방이 만들어 졌습니다.");
				out.println("오픈방이 만들어 졌습니다.");
			}
			roomNoCheck(user,out);
		}catch(Exception e) {
			out.println("/createroom (lock/unlock) password 순으로 기재해주세요.");
		}finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(con!=null) con.close();
		}
	
	}
	public int roomNoReturn (String user) throws SQLException {
		Connection con = ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		int roomNo = 0;
		try {
			String sql = "select room from emp where name ='"+user+"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()) {
				roomNo = rs.getInt("room");
			}
			
		}catch(Exception e) {
			System.out.println("RoomNumber Check 중 Error" + e);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}
		return roomNo;
	}
	//나의 현재방 위치를 출력해줌
	public void roomNoCheck (String user, PrintWriter out) throws SQLException {
		Connection con = ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		try {
			String sql = "select room from emp where name ='"+user+"'";
			int roomNo = 0;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()) {
				roomNo = rs.getInt("room");
			}
			
			System.out.println("현재 당신의 위치는 "+roomNo+"번방 입니다.");
			out.println("현재 당신의 위치는 "+roomNo+"번방 입니다.");
			
		}catch(Exception e) {
			System.out.println("RoomNumber Check 중 Error" + e);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}
	}
	//현재 같은 방에 있는 사용자들에게 메시지를 뿌려주는 영역
	public void sendAllMsg(String user,String msg) throws SQLException, UnsupportedEncodingException{
		
//		Iterator<String> it = clientMap.keySet().iterator();
		PrintWriter it_out=null;
		Connection con = ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
			String sql  ="select name from emp where room = (select room from emp where name = '"+user+"')";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			
			while (rs.next()) {
				try {
					it_out = (PrintWriter)clientMap.get(rs.getString("name"));
					
					if(checkBlock(user,msg)>0) continue;
					if(user.equals("")) {
						it_out.println(URLDecoder.decode(badWordCheck(msg,user),"UTF-8"));
					}else it_out.println(URLDecoder.decode("["+user+"] "+badWordCheck(msg,user),"UTF-8"));
				}catch(Exception e) {
					it_out = clientMap.get(user);
				}
			}
		}catch(Exception e) {
			System.out.println("sendAllmsg 중 Error 1"+e);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}
	}
	//block 멤버 없이 무조건 다 뿌림
	public void noticeAll (String user,String msg) throws SQLException {
		Iterator<String> it = clientMap.keySet().iterator();
		PrintWriter it_out=null;
		String bName ="";	
		
		String str = msg;
		String temp = str.substring(str.indexOf(" ")+1);
		String txt = temp.substring(temp.indexOf(" ")+1);
			
 		while(it.hasNext()) {
 			try {
 				bName = it.next();
 				it_out = (PrintWriter)clientMap.get(bName);

				if(user.equals("")) {
					it_out.println(URLDecoder.decode(badWordCheck(txt,user),"UTF-8"));
				}else it_out.println(URLDecoder.decode("["+user+"의 공지] "+badWordCheck(txt,user),"UTF-8"));
				
 			}catch(Exception e) {
 				System.out.println("noticeAll에서 Error "+e);
 			}
 		}
	}
	// sendAllmsg 하기 전에 block된 멤버가 있는지 확인하는 메소드
	public int checkBlock (String user, String msg) throws SQLException {
		Iterator<String> it = clientMap.keySet().iterator();
		String sql=null;
		PrintWriter it_out=null;
		String bName ="";	
		
		Connection con = ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int count = 0;
		int count1 = 0;
		
 		while(it.hasNext()) {
 				bName = it.next();
 				it_out = (PrintWriter)clientMap.get(bName);
 				count=0;
 				count1=0;
 				
				sql = "select count(*) from block where oname = '"+bName+"' and bname = '"+user+"'";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) count = rs.getInt("count(*)");				
				pstmt.clearParameters();
				
				sql = "select count(*) from block where oname = '"+user+"' and bname = '"+bName+"'";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					count1 = rs.getInt("count(*)");
				}
				count = count + count1;
 		}
 		if(rs!=null) rs.close();
 		if(pstmt!=null)pstmt.close();
 		if(con!=null)con.close();
 		
		return count;
	}
	// '/'로 시작할 경우 명령어라고 생각하고 메소드를 호출함, 이 영역 내에서 각각 메소드를 호출시킴
	public void commendInput(PrintWriter out, String s, String name) throws SQLException, UnsupportedEncodingException, IOException {
		String str = s; //클라이언트에서 받은 text가 됨
		String com= ""; 
		
		try {
			com = str.substring(1, str.indexOf(" "));
		}catch(Exception e){
			com = str.substring(1);
		}
		if(com.equalsIgnoreCase("to")) {
			setWhisper(str,name);
		}else if(com.equalsIgnoreCase("allList")) {
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
		}else if(com.equalsIgnoreCase("notice")) {
			noticeAll(name, str);
		}else if(com.equals("createroom")) {
			createRoom(name,str,out);
		}else if(com.equals("noticeall")) {
			noticeAll(name,str);
		}else if(com.equals("roommemberlist")) {
			roomMemberList(name,out);
		}else if(com.equalsIgnoreCase("joinroom")) {
			joinRoom(name,str,out);
		}else if(com.equals("roomnocheck")) {
			roomNoCheck(name, out);
		}else if(com.equals("roomlist")) {
			roomlist(name,out);
		}else if(com.equalsIgnoreCase("waitingRoomList")) {
			waitingRoomList(name,out);
		}else if(com.equals("mymember")) {
			myMemberList(name,out);
		}
	}
	public void myMemberList(String name, PrintWriter out) throws SQLException {
		Connection con = ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			String sql="select name from emp where room = (select rno from room where room_owner = '"+name+"')";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			
			String keys="내가 만든 대화방 참여자 리스트 [ ";
			while(rs.next()) {
				keys += (String)rs.getString(1)+",";
			}
			keys = keys.substring(0,keys.length()-1)+" ]";
			out.println(keys);
		}
		catch(Exception e) {
			System.out.println("내 방 참여자가 없습니다.");
			System.out.println("myRoomMemberList에서 에러 : "+e);
		}finally { 
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}
	}
	public void roomlist(String name,PrintWriter out) throws SQLException {
		Connection con = ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			String sql="select distinct rno from room";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			String keys="대화방 목록 [";
			while(rs.next()) {
				keys += (String)rs.getString(1)+",";
			}
			keys = keys.substring(0,keys.length()-1)+"]";
			out.println(keys);
		}catch(Exception e) {
			System.out.println("대화방 목록에서 에러 : "+e);
		}finally { 
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}
	}
	
	public void joinRoom(String name, String str, PrintWriter out) throws SQLException, UnsupportedEncodingException, IOException {
		String str1 = str;
		String name1 = name;
		String temp = str1.substring(str1.indexOf(" "));
		String roomNo = temp.substring(1,temp.length());
						
		sqlCall("update emp set room = '"+roomNo+"' where name = '"+name1+"'");
		out.println(roomNo+"방에 입장했습니다.");
	}
	
	//전체 사용자 list를 요청할 경우 list를 뿌려줌
	public void allList(PrintWriter out) {
		Iterator<String> itr = clientMap.keySet().iterator();
		String keys="사용자 리스트 [";
		
		while(itr.hasNext()) {
			keys += (String)itr.next()+",";
		}
		keys = keys.substring(0,keys.length()-1)+"]";
		out.println(keys);
	}
	
	public void waitingRoomList (String name, PrintWriter out) throws SQLException {
		Connection con = ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			String sql="select name from emp where room = '0'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			String keys="대기실에 있는 사용자 리스트 [";
			while(rs.next()) {
				keys += (String)rs.getString(1)+",";
			}
			keys = keys.substring(0,keys.length()-1)+"]";
			out.println(keys);
		}catch(Exception e) {
			System.out.println("waitingRoomList에서 에러 : "+e);
		}finally { 
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}
	}
	public void roomMemberList(String name,PrintWriter out) throws SQLException {
		Connection con = ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			String sql="select name from emp where room = (select room from emp where name = '"+name+"')";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			String keys="대화방 사용자 리스트 [";
			while(rs.next()) {
				keys += (String)rs.getString(1)+",";
			}
			keys = keys.substring(0,keys.length()-1)+"]";
			out.println(keys);
		}catch(Exception e) {
			System.out.println("roomList에서 에러 : "+e);
		}finally { 
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}
		
	}
	//나쁜말인지 체크하는 메소드, sendAllmsg와 whisper에서 확인함
	public String badWordCheck(String str,String name) throws SQLException {
		
		Connection con = ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs = null;
				
		String sql = "select offen from offen_lang where owner = 'server' or owner = '"+name+"'";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery(sql);
				
		ArrayList<String> arr = new ArrayList<String>();
		try {
			while(rs.next()) {
				 arr.add(rs.getString(1));
			}
		}catch(Exception e) {
 			return str;
		}
		
		for(String a : arr) {
			if(str.contains(a)) {
				String star="";
				for(int i=0;i<a.length();i++) {
					star = star+"*";
				}
				str = str.replace(a, star);
				return str;
			}
		}
		
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
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
		Connection con = ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try{
			int[] arr = new int[1];
			String sql="select count(*) from offen_lang where offen = '"+word+"' and owner = '"+name+"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				arr[0] = rs.getInt(1);
			}else {
				out.println("else 문 : 이미 등록된 나쁜말 입니다.");
			}
			
			if(arr[0]>0) {
				out.println(name+"님이 이미 등록된 나쁜말 입니다.");
				return;
			}else {
				sqlCall("insert into offen_lang values ('"+word+"'"+",'"+name+"')");
				System.out.println(name+"님이 "+word+"를 나쁜말 목록에 추가했습니다.");
				out.println(name+"님이 "+word+"를 나쁜말 목록에 추가했습니다.");
			}
			
		}catch(Exception e) {
			out.println("이미 등록된 나쁜말 입니다.");
			return;
		}finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(con!=null)con.close();
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
			myAdd.println(friendName+"님께 귓속말 전송 : "+badWordCheck(txt,name));
			
//			친구한테 메시지 전송
			PrintWriter address = clientMap.get(friendName);
			address.println(name+"님의 귓속말 :"+badWordCheck(txt,name));
		}
	
	//-----------DB접근 메소드 영역---------
	//SQL 쿼리문 전송할 때 매번 필요한 내용들을 담아 메소드로 정의 함
	public static void sqlCall(String iSql) throws SQLException {
		Connection con = ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;

		try {
			String sql = iSql;
			pstmt = con.prepareStatement(sql);
			int updateCount=pstmt.executeUpdate();
			System.out.println("update Count : "+updateCount);
			con.commit();
			if(pstmt!=null) pstmt.close();
			con.close();
		}catch(Exception e) {
			System.out.println("쿼리 호출 시 Error "+e);
		}
	}
	//updateCount 반환하는 메소드
	public static int updateCount(String iSql) throws SQLException {
		Connection con = ConnectionPool.getConnection("성공");
		PreparedStatement pstmt =null;
		int updateCount=0;
		String sql = iSql;
		try {
			pstmt = con.prepareStatement(sql);
			updateCount=pstmt.executeUpdate(sql);
				
			System.out.println("update Count : "+updateCount);
			return updateCount;
		}catch(Exception e) {
			System.out.println("update Count 가 없습니다.");
			return updateCount;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException sqle) {}
		}
	}
	
	
	public static void main(String[] args) throws SQLException {

		MultiServer6 ms = new MultiServer6();
		ms.init();
	}

	//inner class
	class MultiServerT extends Thread {
		Socket socket;
		PrintWriter out = null;
		BufferedReader in = null;
		
		public MultiServerT(Socket socket) throws SQLException {
			
			this.socket=socket;
			try {
				out = new PrintWriter(this.socket.getOutputStream(),true);
				in = new BufferedReader(new InputStreamReader(
						this.socket.getInputStream(),"UTF-8"));
			}catch(Exception e) {
				System.out.println("예외 : "+e);
			}
		}

		public void dbClear(String name) throws SQLException {
			con = ConnectionPool.getConnection("성공");
			PreparedStatement pstmt =null;
			try {
				
				int updateCount=0;
				String sql="";
				sql = "delete from block where oname ="+"'"+name+"'";
				pstmt = con.prepareStatement(sql);
				updateCount = pstmt.executeUpdate();
				System.out.println("Server block delete Count : "+updateCount);
				pstmt.clearParameters();
				
				sql = "delete from emp where name ="+"'"+name+"'";
				pstmt = con.prepareStatement(sql);
				updateCount = pstmt.executeUpdate();
				
				System.out.println("Server emp delete Count : "+updateCount);
				pstmt.clearParameters(); //pstmt close안하고 또 쓸수 있는 메소드!

				sql = "delete from block where oname ="+"'"+name+"'";
				pstmt = con.prepareStatement(sql);
				updateCount = pstmt.executeUpdate();
				System.out.println("Server block delete Count : "+updateCount);
				
			}catch(Exception e) {
				System.out.println("DB Clear 중 Error : "+e);
			}finally { 
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}
		}
		
		public void run() {
			
			String s ="";
			String name ="";
			try {
				
				name = in.readLine();
				name = URLDecoder.decode(name,"UTF-8");
				
				sendAllMsg("",name +"님이 입장하셨습니다.");
				clientMap.put(name, out);
				
				System.out.println("현재 접속자 수는 "+clientMap.size()+"명 입니다.");
				sendAllMsg("","현재 접속자 수는 "+clientMap.size()+"명 입니다.");

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
					dbClear(name);
					
				}catch(Exception e) {
					System.out.println("여기서 에러인가용?");
					e.printStackTrace();
				}finally {	
					try {
						in.close();
						out.close();
						socket.close();
						if(con!=null) con.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}	
		}
	}
}
