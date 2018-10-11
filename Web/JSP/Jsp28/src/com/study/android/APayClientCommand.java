package com.study.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

public class APayClientCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String code = request.getParameter("code");
		ADao dao = ADao.getInstance();
		
		String result;
		int upcount;
		
		JSONObject obj = new JSONObject();
		
		System.out.println("code : "+code);
		result=dao.sendpush(code, "결제가 완료되어 음료를 만드는 중입니다.");
		if(result.equals("성공")) {
			obj.put("results", "ok");
			obj.put("desc", "push 발송완료");
			writer.println(obj);
		}else {
			obj.put("results", "fail");
			obj.put("desc", "다시 시도해 주세요.");
			writer.println(obj);
		}
		writer.close();
	}
}
