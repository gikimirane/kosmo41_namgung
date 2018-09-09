package com.study.jsp.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CExitCommand implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
	
		PrintWriter writer = response.getWriter();
		String id = request.getParameter("id");
		
		CDao dao = CDao.getInstance();
		int exit=0;
		try {
			exit = dao.exit(id);
			
			if(exit==1) {
				writer.println( "[{\"results\":\"ok\",\"desc\":\"none\"}]" );
				
			}else if(exit==0) {
				writer.println( "[{\"results\":\"fail\",\"desc\":\"접속 정보가 없습니다. (exit0)\"}]" );
			}
		} catch (SQLException e) {
			writer.println( "[{\"results\":\"fail\",\"desc\":\"접속 정보가 없습니다.(catch)\"}]" );
		}
	}

}
