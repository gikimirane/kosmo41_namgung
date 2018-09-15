package com.study.jsp.chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CDao {
	
	private static CDao instance = new CDao();
	DataSource dataSource=null;
	
	
	private CDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static CDao getInstance() {
		return instance;
	}
	//이 사람의 방은 몇번인가?
	public int myRoomNo(String name) {
		int roomno=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String query="select room from emp where name=?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				roomno = rs.getInt("room");
			}
			System.out.println(name+"이 위치한 방은 : "+roomno);
		}catch(Exception e) {
			System.out.println("myRoomNo 메소드error");
			e.printStackTrace();
		}finally {
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return roomno;
	}
	public String badWordCheck(String name,String str) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<String> arr = new ArrayList<String>();

		try {
			con = dataSource.getConnection();
			String sql = "select offen from offen_lang where owner = 'server' or owner =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
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
			System.out.println("나쁜말찾는 메소드error");
			e.printStackTrace();
		}finally {
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return str;
	}
	
	public String badWordServer(String str) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<String> arr = new ArrayList<String>();

		try {
			con = dataSource.getConnection();
			String sql = "select offen from offen_lang where owner = 'server'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
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
			System.out.println("서버 나쁜말찾는 메소드error");
			e.printStackTrace();
		}finally {
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return str;
	}
	
	public int setBlockWord(String id,String word) {
		int uCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String query="";
				
		try {
			con = dataSource.getConnection();
			query="select * from offen_lang where (offen =? and owner =?) or (offen=? and owner='server')";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, word);
			pstmt.setString(2, id);
			pstmt.setString(3, word);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//이미 요청자가 설정한 나쁜말
				uCount=0;
			}else {
				SQLCall("insert into offen_lang values ('"+word+"'"+",'"+id+"','all')");
				uCount=1;
			}
			
		}catch(Exception e) {
			System.out.println("userlist 메소드error");
			e.printStackTrace();
			
		}finally {
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return uCount;
	}
	//대기실 사용자 목록
	public String userlist1(int room) {
		String keys=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String query="select name from emp where room=?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, room);
			rs = pstmt.executeQuery();
			keys="[";
			while(rs.next()) {
				keys += rs.getString(1)+", ";
			}
			keys = keys.substring(0,keys.length()-1)+"]";
			
			
		}catch(Exception e) {
			System.out.println("userlist 메소드error");
			e.printStackTrace();
			
		}finally {
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return keys;
	}
	
	public ArrayList<String> userlist(int room){
		ArrayList<String> names = new ArrayList<String>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String query="select name from emp where room=?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, room);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				names.add(rs.getString(1));
			}
			
		}catch(Exception e) {
			System.out.println("userlist 메소드error");
			e.printStackTrace();
			
		}finally {
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return names;
	}
	//초대하는사람, 초대받는사람 2명 값 보내줘야 해
	public int invitation(String mname,String fname) {
		String me = mname;
		String friend = fname;
		int uCount=0;
		
		if(!me.equals(roomOwnerReturn(me))) {
			uCount=2;
		}else {		
			try {
				int myroom = myRoomNo(me);
				SQLCall("update emp set status='invitation' where name = '"+friend+"'");
				SQLCall("update emp set invi_num = '"+myroom+"' where name ='"+friend+"'");
				uCount=1;
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("invitation에서 죽었어요");
			}
		}
		return uCount;
	}
	public ArrayList<String> allUserList(){
		ArrayList<String> list = new ArrayList<String>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
	
		try {
			con = dataSource.getConnection();
			String sql="select name from emp";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			
		}catch(Exception e) {
			System.out.println("대화방 목록에서 에러 : "+e);
			e.printStackTrace();
		}finally { 			
			try{
				 if(rs!=null) rs.close();
				 if(pstmt!=null) pstmt.close();
				 if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}			
		return list;
	}


	//이 방의 비밀번호와 유저가 입력한 비밀번호가 맞는지 확인
	public int password(String uPass,String room) {
		int uCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String query="select pwd from room where rno=?";
		String dPass="";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, room);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dPass=rs.getString(1);
			}
			
			if(dPass.equals(uPass)) {
				uCount=1;
			}else {
				uCount=0;
			}
		}catch(Exception e) {
			System.out.println("Roomlist메소드error");
			e.printStackTrace();
		}finally {
			try{
				 if(rs!=null) rs.close();
				 if(pstmt!=null) pstmt.close();
				 if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return uCount;
	}
	
	public ArrayList<RoomDto> roomlist() {
		ArrayList<RoomDto> dtos = new ArrayList<RoomDto>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String query="select * from room order by rno desc";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String rno = rs.getString(1);
				String user_limit = rs.getString(2);
				String open_type = rs.getString(3);
				String pwd = rs.getString(4);
				String room_owner = rs.getString(5);
				
				RoomDto dto = new RoomDto(rno, user_limit, open_type, pwd, room_owner);
				dtos.add(dto);
			}
		}catch(Exception e) {
			System.out.println("Roomlist메소드error");
			e.printStackTrace();
			
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
			
	public int exit(String id) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		String query = "delete from emp where name=?";
		int uCount=0;
		System.out.println("deleteID : "+id);
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			uCount = pstmt.executeUpdate();
		
		}catch(Exception e) {
			System.out.println("exit메소드error");
			e.printStackTrace();
			uCount=0;
		}finally {
			try{
				 if(pstmt!=null) pstmt.close();
				 if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return uCount;
	}
	public int changeOwner(String id,String newowner) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String query = "update room set room_owner=? where rno=?";
		int uCount=0;
		
		int myroom = myRoomNo(id);
		
		System.out.println("현재 내방번호:"+myroom);
		System.out.println("내이름"+id);
		System.out.println("새로운 방장 :"+newowner);
		
		if(id.equals(roomOwnerReturn(id))){
			try {
				con = dataSource.getConnection();
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, newowner);
				pstmt.setInt(2, myroom);
				rs = pstmt.executeQuery();
				uCount=1;
			}catch(Exception e) {
				System.out.println("changeOwner Error");
				e.printStackTrace();
				
			}finally {
				try{
					 if(rs!=null) rs.close();
					 if(pstmt!=null) pstmt.close();
					 if(con!=null) con.close();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		}else {
			uCount=2;
			return uCount;
		}
		return uCount;
	}
	
	//이 사람이 초대중인지?
	public String statusInvitation(String id) {
		String status="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String query = "select status from emp where name=?";
		
		System.out.println("deleteID : "+id);
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				status = rs.getString(status);
			}
			
		}catch(Exception e) {
			System.out.println("invitationstatus Error");
			e.printStackTrace();
			
		}finally {
			try{
				 if(rs!=null) rs.close();
				 if(pstmt!=null) pstmt.close();
				 if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return status;
	}
	
	//이 유저를 이방으로 옮기세요
	public int changeRoom(String id,String room) throws SQLException {
		ArrayList<RoomDto> dtos = roomlist();
		
		int uCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		String query = "";
		String opentype="";
		String limit ="";
		String pw="";
		String rno="";
		int count=0;
		
		
		
		for(int i=0;i<dtos.size();i++) {
			if(dtos.get(i).rno.equals(room)) {
				rno = dtos.get(i).rno;
				opentype = dtos.get(i).open_type;
				limit = dtos.get(i).user_limit;
				pw = dtos.get(i).pwd;
				count++;
			}
		}		
				
		//유효하지 않은 방일 경우 0으로 return;		
		if(count==0) {
			System.out.println("유효하지 않은 방이라 리턴");
			return uCount;
		}
		
		//제한인원 초과 시 return 2로
		if(limit.equals("0")) {
			System.out.println("제한인원초과");
			uCount=2;
			return uCount;
		}
			
		try {
			con = dataSource.getConnection();
			query = "update emp set room=? where name=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, room);
			pstmt.setString(2, id);
			uCount = pstmt.executeUpdate();
			SQLCall("update room set user_limit=user_limit-1 where rno='"+room+"'");
		
		}catch(Exception e) {
			System.out.println("방 변경 불가");
			e.printStackTrace();
		}finally {
			try{
				 if(pstmt!=null) pstmt.close();
				 if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return uCount;
	}

	public ArrayList<String> roomMember(int ownerroom){
		ArrayList<String> list = new ArrayList<>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String query="";
		
		try {
			con = dataSource.getConnection();
			query = "select name from emp where room = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ownerroom);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString(1));
			
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("나의 방이 없음");
		}finally {
			try{
				 if(rs!=null) rs.close();
				 if(pstmt!=null) pstmt.close();
				 if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return list;
	}
		
	public int deleteroom1(String id) throws SQLException {
		int ownerroom=ownerRoomNo(id);
		
		if(ownerroom!=0) {
			ArrayList<String> rList;
			rList = roomMember(ownerroom);
			
			for(int i=0;i<rList.size();i++) {
				changeRoom(rList.get(i),"0");
				
			}
		}

		int uCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		String query = "delete from room where room_owner=?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			uCount = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("나의 방이 없음");
			e.printStackTrace();
		}finally {
			try{
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return uCount;
	}
	
	public int sameID(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String query = "select name from emp where name=?";
		int uCount=0;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				uCount=1;
			}else {
				uCount=0;
			}
		}catch(Exception e) {
			System.out.println("join메소드error");
			e.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return uCount;
	}
	
	public int join(String id) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		String query = "insert into emp values (?,'0','connect','0')";
		int uCount=0;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			uCount = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("join메소드error");
			e.printStackTrace();
			uCount=0;
		}finally {
			try{
				 if(pstmt!=null) pstmt.close();
				 if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return uCount;
	}
	//ID가 방장인 방 return
	public int ownerRoomNo(String id) {
		int roomno=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		System.out.println("roomNo :"+id);
		String query = "select rno from room where room_owner=?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				roomno = rs.getInt(1);
			}else {
				roomno = 0;
			}
			System.out.println("내가 방장인방 : "+roomno);
			
		}catch(Exception e) {
			System.out.println("roomno 메소드error");
			e.printStackTrace();
			
		}finally {
			try{
				 if(rs!=null) rs.close();
				 if(pstmt!=null) pstmt.close();
				 if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return roomno;
	}
	//사용자가 있는 방의 타입은 무엇인가?
	public String roomTypeReturn(String user) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String type="";
		String sql = "select open_type from room where rno = (select room from emp where name =?)";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				type = rs.getString(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			try {
				 if(rs!=null) rs.close();
				 if(pstmt!=null) pstmt.close();
				 if(con!=null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}	
		return type;
	}
	
	//사용자가 있는 방의 방장은 누구인가?
	public String roomOwnerReturn(String user) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String owner="";
		String sql = "select room_owner from room where rno = (select room from emp where name=?)";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user);
			rs = pstmt.executeQuery();
						
			if(rs.next()) {
				owner = rs.getString("room_owner");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			try {
				 if(rs!=null) rs.close();
				 if(pstmt!=null) pstmt.close();
				 if(con!=null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}	
		return owner;
	}
	
	
	
	//이 방에 방장은 누구인가?
	public String roomowner(int rNum) {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String owner ="";
		String query = "select room_owner from room where rno=?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, rNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				owner = rs.getString(1);
			}else {
				owner = "|";
			}
			System.out.println("방장은 : "+owner);
			
		}catch(Exception e) {
			System.out.println("roomowner 메소드error");
			
		}finally {
			try{
				 if(rs!=null) rs.close();
				 if(pstmt!=null) pstmt.close();
				 if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return owner;
	}
	public int SQLCall(String sql) {
		Connection con=null;
		Statement stmt = null;
		int uCount=0;
		
		try {
		con = dataSource.getConnection();
		stmt = con.createStatement();
		uCount=stmt.executeUpdate(sql);
		System.out.println("쿼리 실행 : "+sql);
		
		}catch(Exception e) {	
			e.printStackTrace();
			System.out.println("쿼리 실행에서 실패 : "+sql);
		}finally {
			try{
				 if(stmt!=null) stmt.close();
				 if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return uCount;
	}
	
	public String openType(String room) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String locktype="";
		String query = "select open_type from room where rno =?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, room);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				locktype=rs.getString(1);
			}
			
		}catch(Exception e) {	
			e.printStackTrace();
			System.out.println("lockType Error");
		}finally {
			try{
				 if(rs!=null) rs.close();
				 if(pstmt!=null) pstmt.close();
				 if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return locktype;
	}

	
	
	
	
	public int newroom(String id,String lock,String limit,String pw) {
		int uCount=0;
			
		if(ownerRoomNo(id)==0) {
			Connection con=null;
			PreparedStatement pstmt=null;
			String query = "insert into room values (rno.nextVal,?,?,?,?)";
			
			try {
				con = dataSource.getConnection();
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, limit);
				pstmt.setString(2, lock);
				pstmt.setString(3, pw);
				pstmt.setString(4, id);
				uCount = pstmt.executeUpdate();
								
				int myroom=ownerRoomNo(id);
				String roomno = Integer.toString(myroom); 
				changeRoom(id, roomno);
				uCount=myroom;
			}catch(Exception e) {
				System.out.println("newroom메소드error");
				e.printStackTrace();
			}finally {
				try{
					 if(pstmt!=null) pstmt.close();
					 if(con!=null) con.close();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		}else {
			return uCount=0;
		}
		return uCount;
	}
}
