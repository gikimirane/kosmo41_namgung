package com.study.jsp.chat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class COwnerChange implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();	
		CDao dao = CDao.getInstance();
		int uCount=0;
		
		String newowner = request.getParameter("user");
		uCount=dao.changeOwner(newowner);
		
		if(uCount==1) {
			writer.println( "[{\"results\":\"ok\",\"desc\":\"방장이 승계되었습니다.\"}]" );
		}else if(uCount==0){
			writer.println( "[{\"results\":\"fail\",\"desc\":\"일시적인 오류로 방장 승계가 불가한 상태입니다.\"}]" );
		}
		
	}

}
