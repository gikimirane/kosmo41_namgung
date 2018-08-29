package com.study.jsp1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BDao {
	
	private static BDao instance = new BDao();
	
	private BDao() {
	}
	
	public static BDao getInstance() {
		return instance;
	}
	
	public void write(String bName, String bTitle, String bContent) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String query = "insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) "
					+ "values "
					+ "(mvc_board_seq.nextval,?,?,?,0,mvc_board_seq.currval,0,0)";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);

			int rn =pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private Connection getConnection() {
		Context context=null;
		DataSource dataSource=null;
		Connection con =null;
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			con = dataSource.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public ArrayList<BDto> list(){
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet resultSet=null;
		String query = "select * from mvc_board order by bGroup desc, bStep asc";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(resultSet!=null) resultSet.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public BDto contentView(String strID) {
		upHit(strID);
		BDto dto = null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet resultSet=null;

		try {
			con = getConnection();
			String query = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
						
				dto = new BDto(bId, bName, bTitle, bContent, bDate, 
						bHit, bGroup, bStep, bIndent);	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(resultSet!=null) resultSet.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	public void modify(String bName, String bTitle, String bContent, String bId) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		String query = "update mvc_board set bName=?, bTitle=?, bContent=? where bId =?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bId));
	
			int rn =pstmt.executeUpdate();
			System.out.println("수정완료 : "+rn);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	private void upHit(String bId) {
		Connection con=null;
		PreparedStatement pstmt=null;
		//나중엔 글쓴이가 눌렀을때는 hit되지 않도록 함, session ID 와 글쓴이 ID와 같으면~~return처리
		String query = "update mvc_board set bHit=bHit+1 where bId =?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			int rn = pstmt.executeUpdate();
						
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public void delete(String bId) {
		Connection con=null;
		PreparedStatement pstmt=null;
		//나중엔 글쓴이가 눌렀을때는 hit되지 않도록 함, session ID 와 글쓴이 ID와 같으면~~return처리
		String query = "delete from mvc_board where bId =?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bId));
			int rn = pstmt.executeUpdate();
						
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public BDto reply_View(String str) {
		
		BDto dto = null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet resultSet=null;

		try {
			con = getConnection();
			String query = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(str));
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
						
				dto = new BDto(bId, bName, bTitle, bContent, bDate, 
						bHit, bGroup, bStep, bIndent);	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(resultSet!=null) resultSet.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
}
