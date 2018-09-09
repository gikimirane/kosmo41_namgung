package com.study.jsp.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CNewRoomCommand implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
	
		PrintWriter writer = response.getWriter();
		String id = request.getParameter("id");
		String locktype=request.getParameter("locktype");
		String limit = request.getParameter("limit");
		String pw = request.getParameter("roompw");
		CDao dao = CDao.getInstance();
		int newroom;
		
		newroom= dao.newroom(id,locktype,limit,pw);
		
		if(newroom==1) {
			writer.println( "[{\"results\":\"ok\",\"desc\":\"none\"}]" );
		}else if(newroom==0) {
			writer.println( "[{\"results\":\"fail\",\"desc\":\"이미 본인이 방장인 방이 있습니다. (exit0)\"}]" );
		}
	}
}
