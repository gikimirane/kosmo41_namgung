package com.study.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

public class AChargeMoneyCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		String code = request.getParameter("code");
		ADao dao = ADao.getInstance();
		
		String id = request.getParameter("id");
		String money = request.getParameter("money");
		
		System.out.println("id : "+id);
		System.out.println("money : "+money);
		JSONObject obj = new JSONObject();
		
		int result=dao.chargeMoney(id,money);
		
		
		if(result==1) {
			obj.put("results", "ok");
			obj.put("desc", "DB Update 완료");
			writer.println(obj);
		}else {
			obj.put("results", "fail");
			obj.put("desc", "다시 시도해 주세요.");
			writer.println(obj);
		}
		writer.close();
		
	}

}
