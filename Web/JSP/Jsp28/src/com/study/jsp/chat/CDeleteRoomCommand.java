package com.study.jsp.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CDeleteRoomCommand implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = response.getWriter();
		String id = request.getParameter("id");
		
		CDao dao = CDao.getInstance();
		
		int delete=0;
		try {
			delete=dao.deleteroom1(id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(delete==1) {
			writer.println( "[{\"results\":\"ok\",\"desc\":\"none\"}]" );
		}
	}
}
