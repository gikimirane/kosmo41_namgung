package com.study.jsp.chat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CMyRoomCommand implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();	
		CDao dao = CDao.getInstance();
		
		String id=request.getParameter("id");
		String myroom=Integer.toString(dao.myRoomNo(id));
		if(myroom.equals("0")) {
			myroom = "대기실";
		}else {
			myroom = myroom+"번방";
		}
		String data = "[{\"myroom\":\""+myroom+"\"}]";
		writer.println(data);
	}
}
