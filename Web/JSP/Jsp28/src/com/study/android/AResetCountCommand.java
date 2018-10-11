package com.study.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

public class AResetCountCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String id = request.getParameter("id");
		ADao dao = ADao.getInstance();
		
		int result=dao.resetCount(id);
		JSONObject obj = new JSONObject();
		
		if(result==1) {
			obj.put("results", "ok");
			obj.put("desc", "Reset 완료");
			writer.println(obj);
		}else {
			obj.put("results", "fail");
			obj.put("desc", "다시 시도해 주세요.");
			writer.println(obj);
		}
		writer.close();
	}

}
