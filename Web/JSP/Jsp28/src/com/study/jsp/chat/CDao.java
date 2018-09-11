package com.study.jsp.chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.study.jsp1.BPageInfo;

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
			
		}catch(Exception e) {
			System.out.println("myRoomNo 메소드error");
			e.printStackTrace();
			
		}finally {
			try{
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return roomno;
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
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
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
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return names;
	}

	//전체 대화방 목록
	public String roomlist(String name) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		String keys=null;
		try {
			con = dataSource.getConnection();
			String sql="select distinct rno from room";
			pstmt = con.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			sql = "select open_type from room";
			pstmt.clearParameters();
			
			pstmt = con.prepareStatement(sql);
			rs1= pstmt.executeQuery();
			pstmt.clearParameters();
			
			keys="방번호 (공개방 여부 Y or N)<br>[";
			String open;
			while(rs.next() && rs1.next()) {
				open = rs1.getString("open_type");
				keys += (String)rs.getString(1)+"("+open+")"+", ";
			}
			keys = keys.substring(0,keys.length()-1)+"]";
			
		}catch(Exception e) {
			System.out.println("대화방 목록에서 에러 : "+e);
			e.printStackTrace();
		}finally { 			
			if(rs!=null) rs.close();
			if(rs1!=null) rs1.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
			
		}
		return keys;
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
			//e.printStackTrace();
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
	
	public int deleteRoom(String id) throws SQLException {
		ArrayList<String> names = new ArrayList<String>();
		int uCount=0;
		int myroom=myRoomNo(id);
		names=userlist(myroom);
		
		for(int i=0;i<names.size();i++) {
			String rUser = names.get(i);
			changeRoom(rUser,"0");
		}
		
		deleteroom(id);	
		return uCount;
	}
	
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
		//현재 방이 내가 방장이고 방을 옮기려고 할 때, 방장이면 폭파되야 함
		if(roomOwnerReturn(id).equals(id)){
			 deleteRoom(id);
		}
		
		//유효하지 않은 방일 경우 0으로 return;		
		if(count==0) {
			return uCount;
		}
		
		//제한인원 초과 시 return 2로
		if(limit.equals("0")) {
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
		String query = "select name from emp room=?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ownerroom);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString(1));
			}
						
		}catch(Exception e) {
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
		
	public int deleteroom(String id) throws SQLException {
		
		int ownerroom=ownerRoomNo(id);
		if(ownerroom==1) {
			ArrayList<String> rList;
			rList = roomMember(ownerroom);
			for(int i=0;i<rList.size();i++) {
				changeRoom(rList.get(i),"0");
			}
		}
		
		int uCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		String query = "delete from room where room_owner = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			uCount = pstmt.executeUpdate();
			
			pstmt.clearParameters();
			
		}catch(Exception e) {
			System.out.println("나의 방이 없음");
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
			//e.printStackTrace();
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
	//사용자가 있는 방의 방장은 누구인가?
	public String roomOwnerReturn(String user) throws SQLException {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String owner="";
		String sql = "select room_owner from room where rno = (select room from emp where name=?)";
		Connection con = dataSource.getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user);
		rs = pstmt.executeQuery();
		pstmt.clearParameters();
		
		if(rs.next()) {
			owner = rs.getString("room_owner");
		}
		
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}catch(Exception e) {
			e.printStackTrace();
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
	public void SQLCall(String sql) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String query=sql;
		
		try {
		con = dataSource.getConnection();
		pstmt = con.prepareStatement(query);
		pstmt.executeQuery();
		System.out.println("쿼리 실행 : "+sql);
		
		}catch(Exception e) {	
			e.printStackTrace();
			System.out.println("쿼리 실행에서 실패 : "+sql);
		}finally {
			try{
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
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
		int uCount;
			
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
				
				pstmt.clearParameters();
				
				//회원테이블에 현재 방번호 업데이트
				query ="update emp set room=rno.currval where name=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, id);
				pstmt.executeQuery();
				pstmt.clearParameters();
				
				//접속인원 1명 빼기 (방장인 본인도 포함이니까)
				query = "update room set user_limit = user_limit-1 where rno ='rno.currval'";
				pstmt = con.prepareStatement(query);
				pstmt.executeQuery();			
				pstmt.clearParameters();
												
				int myroom=ownerRoomNo(id);
								
				return myroom;
			}catch(Exception e) {
				System.out.println("newroom메소드error");
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
		}else {
			return uCount=0;
		}
	}
}