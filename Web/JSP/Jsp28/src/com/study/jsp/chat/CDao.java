package com.study.jsp.chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public int roomNo(String id) {
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
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return roomno;
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
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return owner;
	}
	
	public int newroom (String id,String lock,String limit,String pw) {
		int uCount;
		System.out.println("newRoom id : "+id);
		System.out.println("newRoom lock:"+lock);
		System.out.println("newRoom limit : "+limit);
		System.out.println("newRoom pw : "+pw);
		
		if(roomNo(id)==0) {
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
