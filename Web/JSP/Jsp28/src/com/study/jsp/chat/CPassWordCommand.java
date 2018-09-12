package com.study.jsp.chat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CPassWordCommand implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();	
		CDao dao = CDao.getInstance();
		
		
		String password = request.getParameter("password");
		String roomno = request.getParameter("roomno");
		int uCount=dao.password(password, roomno);
		
		if(uCount==1) {
			writer.println( "[{\"results\":\"ok\",\"desc\":\"비밀번호가 확인되어 방으로 입장합니다.\"}]" );
		}else {
			writer.println( "[{\"results\":\"fail\",\"desc\":\"비밀번호가 일치하지 않습니다.\"}]" );
		}
		
	}

}
