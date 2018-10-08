package com.study.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class APushClientCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String code = request.getParameter("code");
		
		ADao dao = ADao.getInstance();
		String result;
		result=dao.sendpush(code, "음료가 완료되었습니다!");
		System.out.println("result : "+result);
		if(result.equals("성공")) {
			writer.println( "[{\"results\":\"ok\",\"desc\":\"PUSH 발송 완료\"}]" );
		}else {
			writer.println( "[{\"results\":\"fail\",\"desc\":\"다시 시도해 주세요.\"}]" );
		}
		writer.close();
	}

}
