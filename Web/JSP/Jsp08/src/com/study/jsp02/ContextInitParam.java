package com.study.jsp02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContextInitParam")
public class ContextInitParam extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		String id = getServletContext().getInitParameter("database");
		String pw = getServletContext().getInitParameter("connect");
		String path = getServletContext().getInitParameter("path2");
		
		writer.println("<html><head></head><body>");
		writer.println("아이디 : "+id+"<br>");
		writer.println("비밀번호 : "+pw+"<br>");
		writer.println("path : "+path);
		writer.println("</body></html>");
		writer.close();
		
	}

}
