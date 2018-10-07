package com.study.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AAdminLoginCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
	
		PrintWriter writer = response.getWriter();
		String inputId = request.getParameter("id");
		String inputPw=request.getParameter("pw");
		
		if(inputId.equals("admin")&&inputPw.equals("7786")) {
			writer.println("<script language=\"javascript\">alert(\"반갑습니다! PLACIDO ADMIN 님!\"); document.location.href=\"adminpage.jsp\"; </script>");
			writer.close();
		}else {
			writer.println("<script language=\"javascript\">alert(\"ADMIN 정보가 일치하지 않습니다!\"); history.go(-1); </script>");
			writer.close();
		}
	}
}
