package com.study.jsp.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CInvitationCommand implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();	
		CDao dao = CDao.getInstance();
		int uCount=0;
		
		String myname = request.getParameter("id");
		System.out.println("내이름은 : "+myname);
		String friend = request.getParameter("user");
		System.out.println("친구이름 : "+friend);
		
		uCount=dao.invitation(myname, friend);
				
		if(uCount==1) {
			writer.println( "[{\"results\":\"ok\",\"desc\":\"초대 메시지를 보냈습니다.\"}]" );
		}else if(uCount==0){
			writer.println( "[{\"results\":\"fail1\",\"desc\":\"상대방을 초대하지 못했습니다.\"}]" );
		}else if(uCount==2) {
			writer.println( "[{\"results\":\"fail2\",\"desc\":\"이 방에 방장만 초대가 가능합니다.\"}]" );
		}
	}

}
