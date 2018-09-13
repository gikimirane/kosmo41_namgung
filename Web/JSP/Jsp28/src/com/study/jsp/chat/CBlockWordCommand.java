package com.study.jsp.chat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CBlockWordCommand implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();	
		CDao dao = CDao.getInstance();
		
		String id = request.getParameter("id");
		String word = request.getParameter("word");
		
		System.out.println("command id : "+id);
		System.out.println("command word : "+word);
		
		int uCount = dao.setBlockWord(id,word);
		
		if(uCount==1) {
			writer.println( "[{\"results\":\"ok\",\"desc\":\"본인의 나쁜말 리스트에 추가했습니다.\"}]" );
		}else if(uCount==0) {
			writer.println( "[{\"results\":\"fail\",\"desc\":\"이미 서버 또는 본인이 설정한 나쁜말 입니다.\"}]" );
		}
	}
}
