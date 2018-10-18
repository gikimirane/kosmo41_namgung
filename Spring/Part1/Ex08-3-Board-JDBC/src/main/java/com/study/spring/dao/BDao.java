package com.study.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.study.spring.dto.BDto;
import com.study.spring.util.Constant;

public class BDao {
	
	private static BDao instance = new BDao();
	DataSource dataSource=null;
	int listCount=10;
	int pageCount=10;
	
	JdbcTemplate template = null;
	private BDao() {
		template = Constant.template;
	}
	
	public static BDao getInstance() {
		return instance;
	}
	
	public void write(final String bName, final String bTitle,final String bContent) {
		
		template.update(new PreparedStatementCreator(){
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String query = "insert into mvc_board1 (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) "
						+ "values "
						+ "(mvc_board1_seq.nextval,?,?,?,0,mvc_board1_seq.currval,0,0)";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				return pstmt;
			}
		});
	}
		

	public ArrayList<BDto> list(){
		
		String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent " + 
                		"  from mvc_board1 " +
                		" order by bGroup desc, bStep asc";
		
		return (ArrayList<BDto>)template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	/*
	public BPageInfo articlePage(int curPage) {
		//필요에 의해서 return을 하거나 인자를 받아라
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;

		int totalCount=0;

		String query ="select count(*) as total from mvc_board1";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			//totalCount는 DB에서 구해오기
			if(rs.next()) {
				totalCount = rs.getInt("total");
			}
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
		
		System.out.println("curPage"+pinfo.getCurPage());
		System.out.println("totalPage"+pinfo.getTotalPage());
		System.out.println("startPage"+pinfo.getStartPage());
		System.out.println("totalCount"+pinfo.getTotalCount());
		System.out.println("startPage"+pinfo.getStartPage());
				
		return pinfo;
	}
	*/
	public BDto contentView(String strID) {
		upHit(strID);	
		String query = "select * from mvc_board1 where bId ="+strID;
		
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	public void modify(final String bName,final String bTitle,final String bContent, final String bId) {
			
		String query = "update mvc_board1 set bName=?, bTitle=?, bContent=? where bId =?";
		template.update(query,new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
				ps.setInt(4, Integer.parseInt(bId));
			}
		});
	}
	private void upHit(final String bId) {
		String query = "update mvc_board1 set bHit=bHit+1 where bId =?";
		template.update(query,new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bId);
			}
		});
	}
	
	public void delete(final String bId) {
		String query = "delete from mvc_board1 where bId =?";
		template.update(query,new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(bId));
			}
		});
	}
	
	public BDto reply_View(String str) {
		
		String query = "select * from mvc_board1 where bId ="+str;
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
		
	}
	
	public void reply(final String bId,final String bName,final String bTitle,final String bContent,final String bGroup,final String bStep,final String bIndent) {
		System.out.println("reply 메소드");
		replyShape(bGroup,bStep);
		String query = "insert into mvc_board1 (bId,bName,bTitle,bContent,bGroup,bStep,bIndent) values (mvc_board1_seq.nextval,?,?,?,?,?,?)";
		template.update(query,new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
				ps.setInt(4, Integer.parseInt(bGroup));
				ps.setInt(5, Integer.parseInt(bStep)+1);
				ps.setInt(6, Integer.parseInt(bIndent)+1);
			}
		});	
	}
	private void replyShape(final String strGroup,final String strStep) {
		String query = "update mvc_board1 set bStep=bStep+1 where bGroup=? and bStep > ?";
		template.update(query,new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(strGroup));
				ps.setInt(2, Integer.parseInt(strStep));
			}
		});	
	}
}
