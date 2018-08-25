package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/passwordCheck")
public class passwordCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pwCheck(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pwCheck(request,response);
	}
	
	private void pwCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();		
		HttpSession session=request.getSession();
		
		String userPW = request.getParameter("pw");
		String sessionPW = (String)session.getAttribute("pw");
		System.out.println(userPW);
		System.out.println(sessionPW);
		if(userPW.equals(sessionPW)) {
			writer.println( "[{\"results\":\"ok\",\"desc\":\"비밀번호가 일치합니다.\"}]" );
			response.sendRedirect("DeleteProcess.java");
		}else {
			writer.println( "[{\"results\":\"fail\",\"desc\":\"비밀번호가 일치하지 않습니다.\"}]" );
		}
	}
}
