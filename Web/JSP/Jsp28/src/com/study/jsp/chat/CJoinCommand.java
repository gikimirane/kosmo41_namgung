package com.study.jsp.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CJoinCommand implements CCommand{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = response.getWriter();
		String id = request.getParameter("chatname");
				
		CDao dao = CDao.getInstance();
		int join=0;
		try {
			join = dao.join(id);
			int myroom = dao.myRoomNo(id);
			
			if(join==1) {
				writer.println( "[{\"results\":\"ok\",\"desc\":\"현재 방번호는"+myroom+"입니다.\"}]" );
				
			}else if(join==0) {
				writer.println( "[{\"results\":\"fail\",\"desc\":\"중복된 이름이 있습니다. 이름을 변경해주세요.\"}]" );
			}
		} catch (SQLException e) {
			writer.println( "[{\"results\":\"fail\",\"desc\":\"중복된 이름이 있습니다. 이름을 변경해주세요.\"}]" );
		}
	}
}
