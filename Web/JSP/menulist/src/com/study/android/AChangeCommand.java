package com.study.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AChangeCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String code = request.getParameter("code");
		String status = request.getParameter("status");
		ADao dao = ADao.getInstance();
		
		int result = dao.status(status,code);
		System.out.println("성공?");
		if(result==1) {
			writer.println( "[{\"results\":\"ok\",\"desc\":\"DB Update 완료\"}]" );
		
		}else {
			writer.println( "[{\"results\":\"fail\",\"desc\":\"다시 시도해 주세요.\"}]" );
		}
		
	}

}
