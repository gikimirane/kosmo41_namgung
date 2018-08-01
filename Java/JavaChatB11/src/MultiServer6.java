import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
	//owner 지정하고 변경하는 영역 (방장 승계, /changeowner user명
	public void ownerChange(String user,String msg, PrintWriter out) throws UnsupportedEncodingException, SQLException, IOException {
		String sql;
		Connection con=ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String owner ="";
		String str = msg;
		String temp = str.substring(str.indexOf(" "));
		String newOwner = temp.substring(1,temp.length());
		
		int myRoom=roomNoReturn(user);
		int newRoom = roomNoReturn(newOwner);
		//이 방의 owner가 누구인지 확인함.
		try {
			sql = "select room_owner from room where rno = '"+myRoom+"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				owner = rs.getString(1);
			}
			pstmt.clearParameters();
			
			//오너가 맞으면 -> 새로 지정한 사용자랑 나랑 같은 방이면 -> 방장 변경해줌
			if(user.equalsIgnoreCase(owner)) {
				if(myRoom == newRoom) {
					
					sqlCall("update room set room_owner = '"+newOwner+"' where rno = '"+myRoom+"'");
					out.println("방장을 "+newOwner+"님으로 변경했습니다.");
					PrintWriter nOwner = clientMap.get(newOwner);
					nOwner.println(user+"님이 방장으로 지목했습니다.");
				}else {
					out.println("같은방에 있는 사용자가 아닙니다.");
					return;
				}
			
			}else {
				out.println("방장이 아니기 때문에 방장을 변경할 수 없습니다.");
				return;
			}
		}catch(Exception e) {
			System.out.println("방장 변경 시 Error : "+e);
		}finally { 
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}
	}	
	
	public void ownerChange(String user, String newOwner) throws UnsupportedEncodingException, SQLException, IOException {
		String sql;
		PrintWriter out = clientMap.get(user);
		Connection con=ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String owner ="";
	
		int myRoom=roomNoReturn(user);
		int newRoom = roomNoReturn(newOwner);
		//이 방의 owner가 누구인지 확인함.
		try {
			sql = "select room_owner from room where rno = '"+myRoom+"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				owner = rs.getString(1);
			}
			pstmt.clearParameters();
			
			//오너가 맞으면 -> 새로 지정한 사용자랑 나랑 같은 방이면 -> 방장 변경해줌
			if(user.equalsIgnoreCase(owner)) {
				if(myRoom == newRoom) {
					
					sqlCall("update room set room_owner = '"+newOwner+"' where rno = '"+myRoom+"'");
					out.println("방장을 "+newOwner+"님으로 변경했습니다.");
					PrintWriter nOwner = clientMap.get(newOwner);
					nOwner.println(user+"님이 방장으로 지목했습니다.");
				}else {
					out.println("같은방에 있는 사용자가 아닙니다.");
					return;
				}
			
			}else {
				out.println("방장이 아니기 때문에 방장을 변경할 수 없습니다.");
				return;
			}
		}catch(Exception e) {
			System.out.println("방장 변경 시 Error : "+e);
		}finally { 
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}
	}	
	
	//방 폭파하는 메소드, 방장만 가능한지 체크함
	public void breakDownRoom (String user,PrintWriter out) throws SQLException, UnsupportedEncodingException, IOException {
		Connection con=ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs=null;
	
		String owner="";
		String sql = "select room_owner from room where rno = (select room from emp where name='"+user+"')";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery(sql);
		while(rs.next()) {
			owner = rs.getString("room_owner");
		}
		pstmt.clearParameters();
		PrintWriter mem_out=null;
		if(user.equalsIgnoreCase(owner)) {
			sql = "select name from emp where room = (select rno from room where room_owner = '"+user+"')";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			pstmt.clearParameters();
			while(rs.next()) {
				String name =rs.getString(1);
				joinRoom(name,"/joinroom 0");
				mem_out = clientMap.get(name);
				mem_out.println("방장에 의해 방이 폭파되어 대기실로 이동합니다.");
			}
			sql = "delete from room where room_owner = '"+user+"'";
			pstmt = con.prepareStatement(sql);
			int deletecount = pstmt.executeUpdate();
			System.out.println("방삭제 Count : "+deletecount);
		}else {
			out.println("방장이 아니면 방을 폭파시킬 수 없습니다.");
		}
	}
	//방 나가서 대기실로 이동하는 메소드
	public void roomExit(String user,String msg,PrintWriter out) throws UnsupportedEncodingException, SQLException, IOException {
		joinRoom(user,"/joinroom 0",out);
		System.out.println("방을 나갔습니다.");
		out.println("방을 나가 대기실로 이동했습니다.");
		roomNoCheck(user,out);
	}
	//방 만드는 영역, /createroom unlock or /createroom lock 1234
	public void createRoom(String user,String str,PrintWriter out) throws SQLException {
		Connection con=ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql ="";
		String[] str1 = str.split(" ");
		sql = "select count(*) from room where room_owner = '"+user+"'";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery(sql);
		int count=0;
		while(rs.next()) {
			count = rs.getInt("count(*)");
		}
		if(count>0) {
			out.println("본인이 방장인 방이 이미 있습니다.");
			return;
		}
		
		try {
			if (str1[1].equalsIgnoreCase("비공개")) {
				sqlCall("insert into room values (rno.nextVal,'"+str1[2]+"','n','"+str1[3]+"','"+user+"')");	
				sqlCall("update emp set room =rno.CURRVAL where name='"+user+"'");
				String myroom = Integer.toString(roomNoReturn(user));
				System.out.println("내가 만든 방번호는? : "+myroom);
				sqlCall("update room set user_limit = user_limit-1 where rno = '"+myroom+"'");
				System.out.println("비공개방이 만들어 졌습니다.");
				out.println("비공개방이 만들어 졌습니다.");
				
			}
			else if(str1[1].equalsIgnoreCase("공개")){
				
				sqlCall("insert into room values (rno.nextVal,'"+str1[2]+"','y',' ','"+user+"')");
				sqlCall("update emp set room =rno.CURRVAL where name='"+user+"'");
				String myroom = Integer.toString(roomNoReturn(user));
				System.out.println("내가 만든 방번호는? : "+myroom);
				sqlCall("update room set user_limit = user_limit-1 where rno = '"+myroom+"'");
				System.out.println("공개방이 만들어 졌습니다.");
				out.println("공개방이 만들어 졌습니다.");
				
			}
			roomNoCheck(user,out);
		}catch(Exception e) {
			out.println("/createroom (비공개 or 공개) 정원수 password 순으로 기재해주세요.");
			out.println("예 ) /createroom 비공개 5 1234");
		}finally {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(con!=null) con.close();
		}
	
	}
	//현재 내가 있는 Room No를 보여줌
	public int roomNoReturn (String user) throws SQLException {
		Connection con=ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
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
		Connection con=ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
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
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String it_name;
		con = ConnectionPool.getConnection("성공");	
		try {
			String sql  ="select name from emp where room = (select room from emp where name = '"+user+"')";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				try {
					it_name = rs.getString("name");
					it_out = (PrintWriter)clientMap.get(it_name);
					
					if(checkBlock(user,it_name)>0) {
						continue;
					}
					else if(badWordOwner(it_name)==true) {
						if(user.equals("")) {
							it_out.println(URLEncoder.encode(badWordCheck(msg,it_name),"UTF-8"));
						}else it_out.println(URLEncoder.encode("["+user+"] "+badWordCheck(msg,it_name),"UTF-8"));
					}else if (badWordOwner(it_name)==false){
						if(user.equals("")) {
							it_out.println(URLEncoder.encode(badWordAllCheck(msg),"UTF-8"));
						}else it_out.println(URLEncoder.encode("["+user+"] "+badWordAllCheck(msg),"UTF-8"));
					}					
					//내가 설정한 나쁜말이 있는지 체크
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
	//block 멤버 없이 무조건 다 메시지를 전송함
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

 				if(badWordOwner(bName)==true) {
					if(user.equals("")) {
						it_out.println(URLEncoder.encode(badWordCheck(msg,bName),"UTF-8"));
					}else it_out.println(URLEncoder.encode("["+user+"] "+badWordCheck(msg,bName),"UTF-8"));
				}else if (badWordOwner(bName)==false){
					if(user.equals("")) {
						it_out.println(URLEncoder.encode(badWordAllCheck(msg),"UTF-8"));
					}else it_out.println(URLEncoder.encode("["+user+"] "+badWordAllCheck(msg),"UTF-8"));
				}
				
 			}catch(Exception e) {
 				System.out.println("noticeAll에서 Error "+e);
 			}
 		}
	}
	// 메시지를 뿌려주기 전에 block된 멤버가 있는지 확인하는 메소드
	public int checkBlock (String user, String nowUser) throws SQLException {
		Iterator<String> it = clientMap.keySet().iterator();
		String sql;	
		Connection con=ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count = 0;

		sql = "select count(*) from block where (oname = '"+nowUser+"' and bname = '"+user+"') or (oname = '"+user+"' and bname ='"+nowUser+"')";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			count = rs.getInt("count(*)");				
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
		}else if(com.equalsIgnoreCase("noticeall")) {
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
		}else if(com.equalsIgnoreCase("roomExit")) {
			roomExit(name,str,out);
		}else if(com.equalsIgnoreCase("forceExit")) {
			forceExit(name, str,out);
		}else if(com.equalsIgnoreCase("BreakRoom")) {
			breakDownRoom(name,out);
		}else if(com.equalsIgnoreCase("changeowner")) {
			ownerChange(name,str,out);
		}else if(com.equalsIgnoreCase("invitation")) {
			invitation(name,str,out);
		}else if(com.equalsIgnoreCase("y")) {
			invitationY(name, str,out);
			sqlCall("update emp set status = 'connect' where status = 'invitation'");
		}else if(com.equalsIgnoreCase("n")){
			out.println("초대에 거절했습니다.");
			sqlCall("update emp set status = 'connect' where status = 'invitation'");
			return;
		}else {
			System.out.println("올바른 명령어를 입력하세요.");
		}
	}
	//내가 만든 방에 참여한 멤버를 보여주는 List 영역
	public void myMemberList(String name, PrintWriter out) throws SQLException {
		Connection con=ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSet rs1 =null;
		String myroom="";
		try {
			String sql="select name from emp where room = (select rno from room where room_owner = '"+name+"')";
			pstmt = con.prepareStatement(sql);
			pstmt.clearParameters();
			rs = pstmt.executeQuery(sql);
			sql = "select rno from room where room_owner = '"+name+"'";
			rs1 = pstmt.executeQuery(sql);
			while(rs1.next()) myroom=rs1.getString(1);
			if(myroom.isEmpty()) {
				out.println("내가 만든방이 없습니다.");
				return;
			}else {
				String keys="내가 만든 "+myroom+"번방 참여자 리스트 [ ";
				while(rs.next()&&rs1.next()) {
					keys += (String)rs.getString(1)+",";
				}
				keys = keys.substring(0,keys.length()-1)+" ]";
				out.println(keys);
			}
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
	//현재 있는 Room에 list를 보여줌
	public void roomlist(String name,PrintWriter out) throws SQLException {
		Connection con=ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		try {
			String sql="select distinct rno from room";
			pstmt = con.prepareStatement(sql);			
			rs = pstmt.executeQuery(sql);
			
			sql = "select open_type from room";
			pstmt.clearParameters();
			pstmt = con.prepareStatement(sql);
			rs1= pstmt.executeQuery(sql);
			String keys="대화방 목록 (공개방 여부 Y or N) [";
			String open;
			while(rs.next() && rs1.next()) {
				open = rs1.getString("open_type");
				keys += (String)rs.getString(1)+"("+open+")"+",";
			}
			keys = keys.substring(0,keys.length()-1)+"]";
			out.println(keys);
		}catch(Exception e) {
			System.out.println("대화방 목록에서 에러 : "+e);
		}finally { 
			if(rs!=null) rs.close();
			if(rs1!=null) rs1.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}
	}
	//방장만 강퇴할 수 있음, 1차 체크 : 방장인지? 2차 체크 : 강퇴대상이 같은방에 있는지
	public void forceExit(String name, String str, PrintWriter out) throws SQLException {
		Connection con=ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		String owner ="";
		String msg = str;
		String[] arr = msg.split(" ");
		String mem="";
		
		if(arr.length == 1) {
			out.println("/ForceExit 방사용자명 으로 입력하세요.");
			return;
		}else {
			mem = arr[1];
		}
		PrintWriter mem_out =clientMap.get(mem);
		try {
			String sql = "select room_owner from room where rno = (select room from emp where name='"+name+"')";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			while(rs.next()) {
				owner = rs.getString("room_owner");
				if(name.equalsIgnoreCase(owner)) {
					pstmt.clearParameters();
					
					//owner의 룸번호 가져오기
					sql = "select room from emp where name ='"+owner+"'";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery(sql);
					int oRoomNo=0;
					while(rs.next()) oRoomNo = rs.getInt(1);
					pstmt.clearParameters();
					sql = "select room from emp where name ='"+mem+"'";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery(sql);
					int uRoomNo=0;
					while(rs.next()) uRoomNo = rs.getInt(1);

					if(oRoomNo==uRoomNo) {
						joinRoom(mem,"/joinroom 0",out);
						out.println(mem+"님을 강퇴시켰습니다.");
						mem_out.println("방장에 의하여 강퇴당했습니다. 대기실로 이동합니다.");
					}else {
						System.out.println("강퇴 대상이 같은방에 없습니다.");
						out.println(mem+"님은 같은방에 없습니다.");
					}
					
				}else {
					out.println("방장인 "+owner+"만 강퇴가 가능합니다.");
				}
			}		
			System.out.println("owner는? : "+owner);
		}catch(Exception e) {
			System.out.println("forceExit Error "+e);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}
	}
	//원하는 대화방에 참여할 수 있는 메소드, 아직 password 있는 방에 대한 처리는 하지 않음.
	public void joinRoom(String name, String msg, PrintWriter out) throws SQLException, UnsupportedEncodingException, IOException {
		String str = msg;
		String temp = str.substring(str.indexOf(" "));
		String[] arr = msg.split(" ");
		String roomNo = arr[1];
		int userRoom = roomNoReturn(name);
		String openType ="";
		Connection con=ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String limit = "";
		String sql = "select open_type from room where rno ='"+roomNo+"'";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery(sql);

		while(rs.next()) openType = rs.getString(1);
		pstmt.clearParameters();
		sql = "select user_limit from room where rno='"+roomNo+"'";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery(sql);
		while(rs.next()) limit = rs.getString("user_limit");
		
		if(limit.equalsIgnoreCase("0")) {
			out.println("정원 초과로 입장하실 수 없습니다.");
			System.out.println("정원 초과로 입장 불가, 메소드 return");
			return;
		}
		if(roomNo.equalsIgnoreCase("0")) {
			sqlCall("update room set user_limit=user_limit+1 where rno = '"+userRoom+"'");
			sqlCall("update emp set room = '"+roomNo+"' where name = '"+name+"'");
			roomNoCheck(name,out);
			return;
		}
	
		if(openType.equalsIgnoreCase("y")) {
			sqlCall("update room set user_limit=user_limit-1 where rno = '"+roomNo+"'");
			sqlCall("update emp set room = '"+roomNo+"' where name = '"+name+"'");
			roomNoCheck(name,out);
		}else if(openType.equalsIgnoreCase("n")){
			String pwd="";
			try {
				pwd = arr[2];
			}catch(Exception e) {
				out.println("비밀번호 방입니다. /joinroom 방번호 비밀번호 순으로 입력하세요.");
				return;
			}
			sql = "select pwd from room where rno = '"+roomNo+"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			String roompwd = "";
			while(rs.next()) {
				roompwd = rs.getString("pwd");
			}
			if(pwd.equalsIgnoreCase(roompwd)) {
				System.out.println("비밀번호가 맞아 user 입장합니다.");
				sqlCall("update room set user_limit=user_limit-1 where rno = '"+roomNo+"'");
				sqlCall("update emp set room = '"+roomNo+"' where name = '"+name+"'");
				roomNoCheck(name,out);
			}else {
				out.println("비밀번호가 틀립니다.");
				return;
			}		
		}
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
		if(con!=null) con.close();
	}
	//오버로딩, 강퇴할때 사용함 소리없이 내보냄..
	public void joinRoom(String name, String msg) throws SQLException, UnsupportedEncodingException, IOException {
		String str = msg;
		String temp = str.substring(str.indexOf(" "));
		String[] arr = msg.split(" ");
		String roomNo = arr[1];
		
		String openType ="";
		Connection con=ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql = "select open_type from room where rno ='"+roomNo+"'";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery(sql);
		
		while(rs.next()) openType = rs.getString(1);
		pstmt.clearParameters();
		
		if(openType.equalsIgnoreCase("y")) {
			sqlCall("update emp set room = '"+roomNo+"' where name = '"+name+"'");
//			roomNoCheck(name,out);
		}else if(openType.equalsIgnoreCase("n")){
			String pwd="";
			try {
				pwd = arr[2];
			}catch(Exception e) {
//				out.println("비밀번호 방입니다. /joinroom 방번호 비밀번호 순으로 입력하세요.");
				return;
			}
			sql = "select pwd from room where rno = '"+roomNo+"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			String roompwd = "";
			while(rs.next()) {
				roompwd = rs.getString("pwd");
			}
			if(pwd.equalsIgnoreCase(roompwd)) {
				System.out.println("비밀번호 맞음! 입장 가능~");
				sqlCall("update emp set room = '"+roomNo+"' where name = '"+name+"'");
//				roomNoCheck(name,out);
			}else {
//				out.println("비밀번호가 틀립니다.");
				return;
			}		
		}
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
		if(con!=null) con.close();
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
	//대기실 멤버 list 확인 메소드
	public void waitingRoomList (String name, PrintWriter out) throws SQLException {
		Connection con=ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs=null;
	
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
	//같은 룸에 있는 멤버 확인하는 메소드
	public void roomMemberList(String name,PrintWriter out) throws SQLException {
		Connection con=ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
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
	
	public boolean badWordOwner(String name) throws SQLException {
		boolean sw=false;
		Connection con = ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql;
		sql = "select count(offen) from offen_lang where owner ='"+name+"'";
		try{
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			sw = rs.next();
		}catch(Exception e) {
			System.out.println("badWordOwner Error "+e);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}	
		return sw;
	}
	//server에만 있는 나쁜말 찾는 거
	public String badWordAllCheck(String str) throws SQLException {
		Connection con = ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "select offen from offen_lang where owner = 'server'";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery(sql);

		ArrayList<String> arr = new ArrayList<String>();
		try {
			while(rs.next()) {
				 arr.add(rs.getString(1));
			}
			for(String a : arr) {
				if(str.contains(a)) {
					String star="";
					for(int i=0;i<a.length();i++) {
						star = star+"*";
					}
					str = str.replace(a, star);
				}
			}
		}catch(Exception e) {
 			return str;
		}
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}catch(SQLException sqle) {}
		
		return str;
	}
	//server + 본인이 설정한 나쁜말 찾는거, sendAllmsg와 whisper에서 확인함
	public String badWordCheck(String str,String name) throws SQLException {
		Connection con = ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "select offen from offen_lang where owner = 'server' or owner ='"+name+"'";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery(sql);

		ArrayList<String> arr = new ArrayList<String>();
		try {
			while(rs.next()) {
				 arr.add(rs.getString(1));
			}
			for(String a : arr) {
				if(str.contains(a)) {
					String star="";
					for(int i=0;i<a.length();i++) {
						star = star+"*";
					}
					str = str.replace(a, star);
				}
			}
		}catch(Exception e) {
 			return str;
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
		Connection con=ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs=null;
				
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
				sqlCall("insert into offen_lang values ('"+word+"'"+",'"+name+"','all')");
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
		sqlCall("insert into block values ('"+friendName+"','"+name+"')");
		System.out.println(name+"님이 "+friendName+"님을 차단목록에 추가했습니다.");
		out.println(name+"님이 "+friendName+"님을 차단목록에 추가했습니다.");
	}
	//친구차단 해제 영역
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
	public void setWhisper(String input, String user) throws SQLException, UnsupportedEncodingException {

			String msg = input;
			String temp = msg.substring(msg.indexOf(" ")+1);
			String friendName = temp.substring(0,temp.indexOf(" "));
			String txt = temp.substring(temp.indexOf(" ")+1);

//			이건 귓속말 시작한 사람한테 띄워줌
			PrintWriter myAdd = clientMap.get(user);
			if(badWordOwner(user)==true) {
				myAdd.println(URLEncoder.encode("["+friendName+"]님께 귓속말 : "+badWordCheck(txt,user),"UTF-8"));
			}else if (badWordOwner(user)==false){
				myAdd.println(URLEncoder.encode("["+friendName+"]님께 귓속말 : "+badWordAllCheck(txt),"UTF-8"));
			}
			
//			친구한테 메시지 전송
			PrintWriter address = clientMap.get(friendName);
			if(badWordOwner(user)==true) {
				address.println(URLEncoder.encode("["+user+"]님의 귓속말 : "+badWordCheck(txt,friendName),"UTF-8"));
			}else if (badWordOwner(user)==false){
				address.println(URLEncoder.encode("["+user+"]님의 귓속말 : "+badWordAllCheck(txt),"UTF-8"));
			}
		}
	public void invitation(String name1,String str1, PrintWriter out) throws SQLException {
		String str = str1;
		String name = name1;
		String temp = str.substring(str.indexOf(" "));
		String friendName = temp.substring(1,temp.length());
		PrintWriter f_out = clientMap.get(friendName);
		int roomNo = roomNoReturn(name);
		sqlCall("update emp set status = 'invitation' where name = '"+friendName+"'");
		sqlCall("update emp set invi_num = '"+roomNo+"' where name ='"+friendName+"'");
		out.println(friendName+"님께 초대 메시지를 전송했습니다.");
		f_out.println(name+"님이 초대했습니다. 수락은 Y, 거절은 N을 눌러주세요.");
	}
	public void invitationY(String name,String str, PrintWriter out) throws SQLException {
		Connection con=ConnectionPool.getConnection("성공");
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql = "";
		int invi_room =0;
		try {
			sql = "select invi_num from emp where name = '"+name+"'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			while(rs.next()) invi_room = rs.getInt(1); 
			sqlCall("update emp set room = '"+invi_room+"' where name ='"+name+"'");
			out.println("초대에 응하여 "+invi_room+"방으로 이동합니다.");
		}catch(Exception e) {
			System.out.println("초대 수락 시! "+e);
		}finally { 
			if(rs !=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}		
	}
	
	//-------------------DB접근 메소드 영역----------------//
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
			updateCount=pstmt.executeUpdate();
				
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
	//main 영역, Thread 시작!
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
				
		//Thread 종료 시 삭제해야 하거나 update 할 거 있으면 호출하는 메소드
		public void dbClear(String name,String str,PrintWriter out) throws SQLException {
			Connection con=ConnectionPool.getConnection("성공");
			PreparedStatement pstmt=null;
						
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
		
			}catch(Exception e) {
				System.out.println("DB Clear 중 Error : "+e);
			}finally { 
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}
		}
		public boolean status (String name) throws SQLException {
			boolean sw = false;
			Connection con=ConnectionPool.getConnection("성공");
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				String sql = "select status from emp where name ='"+name+"'";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery(sql);
				String status ="";
				while(rs.next()) {
					status = rs.getString(1);
				}
				if(status.equalsIgnoreCase("invitation")) {
					sw = true;
				}else sw = false;
			}catch(Exception e) {
				System.out.println("status 확인 중 error : "+e);
			}finally { 
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}
			return sw;
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
					
					if(status(name)==true) {
						if(s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")) {
							s = "/"+s;
						}
						else {
							out.println("초대 응답메시지가 아닙니다. Y or N 으로 입력하세요.");
							continue;
						}
					}
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
					dbClear(name,s,out);
					
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
