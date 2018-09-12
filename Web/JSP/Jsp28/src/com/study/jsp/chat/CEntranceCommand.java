package com.study.jsp.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CEntranceCommand implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = response.getWriter();
		
		String rno = request.getParameter("rno"); 		
		String id = request.getParameter("id");
		System.out.println("rno : "+rno);
		System.out.println("id : "+id);
		
		CDao dao = CDao.getInstance();
		int uCount = 0;
		try {
			uCount = dao.changeRoom(id, rno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(uCount==1) {
			writer.println( "[{\"results\":\"ok\",\"desc\":\"현재 방번호는"+rno+"입니다.\"}]" );
		}else {
			writer.println( "[{\"results\":\"fail\",\"desc\":\"요청하신 방에 입장할 수 없습니다.\"}]" );
		}	
	}
}
