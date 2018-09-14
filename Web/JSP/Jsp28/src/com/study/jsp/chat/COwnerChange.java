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
		
		
		String id = request.getParameter("id");
		String newowner = request.getParameter("user");
		
		System.out.println("새로운 유저 parameter : "+newowner);
		
		uCount=dao.changeOwner(id,newowner);
		
		if(uCount==1) {
			writer.println( "[{\"results\":\"ok\",\"desc\":\"방장이 승계되었습니다.\"}]" );
		}else if(uCount==0){
			writer.println( "[{\"results\":\"fail1\",\"desc\":\"일시적인 오류로 방장 승계가 불가한 상태입니다.\"}]" );
		}else if(uCount==2) {
			writer.println( "[{\"results\":\"fail2\",\"desc\":\"이 방의 방장만 승계가 가능합니다.\"}]" );
		}
		
	}

}
