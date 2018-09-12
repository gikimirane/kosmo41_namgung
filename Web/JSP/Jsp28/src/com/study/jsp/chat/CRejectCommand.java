package com.study.jsp.chat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CRejectCommand implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();	
		CDao dao = CDao.getInstance();
		int uCount=0;
		
		String myname = request.getParameter("id");
		System.out.println("내이름은 : "+myname);
		uCount=dao.SQLCall("update emp set status='connect',invi_num='0' where name='"+myname+"'");
		
		if(uCount==1) {
			writer.println( "[{\"results\":\"ok\",\"desc\":\"현재 상태는 초대를 받을 수 있습니다.\"}]" );
		}else if(uCount==0){
			writer.println( "[{\"results\":\"fail\",\"desc\":\"일시적인 오류로 초대를 받을 수 없는 상태입니다.\"}]" );
		}
		
	}
}
