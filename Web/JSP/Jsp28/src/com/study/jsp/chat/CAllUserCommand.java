package com.study.jsp.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CAllUserCommand implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();	
		CDao dao = CDao.getInstance();
		
		ArrayList<String> list = dao.allUserList();
		
		String data="[";
		for(int i=0;i<list.size();i++) {
			data += "{\"user\":\""+list.get(i)+"\"},";
		}
		
		data = data.substring(0,data.length()-1)+"]";
		writer.println(data);
	}
}
