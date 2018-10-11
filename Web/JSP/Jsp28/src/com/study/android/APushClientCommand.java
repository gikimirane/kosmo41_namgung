package com.study.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

public class APushClientCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String code = request.getParameter("code");
		JSONObject obj = new JSONObject();
		ADao dao = ADao.getInstance();
		String result;
		result=dao.sendpush(code, "음료가 완료되어 카운터에서 기다리고 있습니다!");
		System.out.println("result : "+result);
		if(result.equals("성공")) {
			obj.put("results", "ok");
			obj.put("desc", "push 발송완료");
			writer.println(obj);
		}else {
			obj.put("results", "fail");
			obj.put("desc", "다시 시도해 주세요");
			writer.println(obj);
		}
		writer.close();
	}

}
