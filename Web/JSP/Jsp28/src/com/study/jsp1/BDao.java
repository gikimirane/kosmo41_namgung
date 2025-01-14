package com.study.jsp1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class BDao {
	
	private static BDao instance = new BDao();
	DataSource dataSource=null;
	int listCount=10;
	int pageCount=10;
	
	private BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BDao getInstance() {
		return instance;
	}
	
	public void write(String bName, String bTitle, String bContent, String fType,String fPath,String bPass) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		String query = "insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent,fType,fPath, bPass)"
				+ "values "
				+ "(mvc_board_seq.nextval,?,?,?,0,mvc_board_seq.currval,0,0,?,?,?)";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);	
			pstmt.setString(4, fType);
			pstmt.setString(5, fPath);
			pstmt.setString(6, bPass);
			
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

	public ArrayList<BDto> list(int curPage, String search, String input){
				
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet resultSet=null;
		String query="";
		
		int count=0;

		int start = (curPage-1)*listCount+1;
		int end = (curPage-1)*listCount+listCount;
		
		try {
			con = dataSource.getConnection();
			
			if(input.equals("0")) {
				query = "select * from (select rownum num, A.* from (select * from mvc_board order by bgroup desc, bstep asc) A where rownum <= ? ) B Where B.num >= ?";
				count=0;
				
			}else {
				input = "'%"+input+"%'";
				query = "select * from (select rownum num, A.* from (select * from mvc_board where "+search+" like "+input+" order by bgroup desc, bstep asc) A where rownum <= ? ) B Where B.num >= ?";
				count=1;
			}

			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
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
				String fType = resultSet.getString("fType");
				String fPath = resultSet.getString("fPath");
				String bPass = resultSet.getString("bPass");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent,fType,fPath,bPass);
				dtos.add(dto);
				
				//request.setAttribute해도 되고
				//ArrayList로 해도 되고
			}
		}catch(Exception e) {
			System.out.println("list catch");
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

	public BPageInfo articlePage(int curPage, String search, String input) {
		//필요에 의해서 return을 하거나 인자를 받아라
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String query = null;
		int count=0;
		System.out.println("search : "+search);
		System.out.println("input : "+input);
		
		int totalCount=0;
		int start = (curPage-1)*listCount+1;
		int end = (curPage-1)*listCount+listCount;
		
		
		try {
			con = dataSource.getConnection();
			if(input.equals("0")) {
				query ="select count(*) as total from mvc_board";
			}else {
				input = "'%"+input+"%'";
				query = "select count(*) as total from mvc_board where "+search+" like "+input;
				count=1;
			}
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			//totalCount는 DB에서 구해오기
			if(rs.next()) {
				totalCount = rs.getInt("total");
			}
			System.out.println(totalCount);
		}catch(Exception e) {
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
				
		//총 페이지 수
		int totalPage;
		if(totalCount%listCount>0) {
			totalPage = (totalCount/listCount)+1;
		}else {
			totalPage = totalCount/listCount;
		}
		
		//현재 페이지
		int myCurPage = curPage;
		if(myCurPage>totalPage) 
			myCurPage = totalPage;
		if(myCurPage<1) 
			myCurPage=1;
		
		int startPage = ((myCurPage-1)/pageCount)*pageCount+1;
		
		
		int endPage = startPage+pageCount-1;
		if(endPage > totalPage) 
			endPage = totalPage;
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(myCurPage);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		pinfo.setTotalPage(totalPage);
		pinfo.setListCount(listCount);
						
		return pinfo;
	}
	
	public BDto contentView(String strID) {
		upHit(strID);
		
		BDto dto = null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet resultSet=null;

		try {
			con = dataSource.getConnection();
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
				String fType = resultSet.getString("fType");
				String fPath = resultSet.getString("fPath");
				String bPass = resultSet.getString("bPass");
								
				dto = new BDto(bId, bName, bTitle, bContent, bDate, 
						bHit, bGroup, bStep, bIndent,fType,fPath,bPass);	
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
			con = dataSource.getConnection();
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
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			int rn = pstmt.executeUpdate();
			System.out.println("히트다 히트!");
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
			con = dataSource.getConnection();
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
			con = dataSource.getConnection();
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
				String bType = resultSet.getString("fType");
				String bPath = resultSet.getString("fPath");
				String bPass = resultSet.getString("bPass");
						
				dto = new BDto(bId, bName, bTitle, bContent, bDate, 
						bHit, bGroup, bStep, bIndent,bType,bPath, bPass);	
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
	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent) {
		System.out.println("reply 메소드");
		replyShape(bGroup,bStep);
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board (bId,bName,bTitle,bContent,bhit,bGroup,bStep,bIndent,ftype,fpath,bpass) values (mvc_board_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, 0);
			pstmt.setInt(5, Integer.parseInt(bGroup));
			pstmt.setInt(6, Integer.parseInt(bStep)+1);
			pstmt.setInt(7, Integer.parseInt(bIndent)+1);
			pstmt.setString(8, "none");
			pstmt.setString(9, "none");
			pstmt.setString(10, "|none");
			
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
	private void replyShape(String strGroup, String strStep) {
	
		Connection con=null;
		PreparedStatement pstmt=null;
		
		String query = "update mvc_board set bStep=bStep+1 where bGroup=? and bStep > ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));
			
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
	
}
