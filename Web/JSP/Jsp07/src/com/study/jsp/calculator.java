package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class calculator extends HttpServlet {
	
	//성공하면 호출하도록
	void doSuccess(HttpServletRequest request, HttpServletResponse response,
			String n1, String n2, String cal) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		int num1 = Integer.parseInt(n1);
		int num2 = Integer.parseInt(n2);
		int result=0;
		
		if(cal.equalsIgnoreCase("add")) {
			result = num1+num2;
		}else if(cal.equalsIgnoreCase("sub")) {
			if(num1>num2) {
				result = num1-num2;
			}else {
				result = num2-num1;
			}
		}else if(cal.equalsIgnoreCase("mul")) {
			result = num1*num2;
		}else if(cal.equalsIgnoreCase("div")) {
			if(num1==0 || num2==0) {
				writer.println("0으로 나눌 수 없습니다.");
				return;
			}else {
				result = num1/num2;
			}
		}
		writer.println("연산결과는 : "+result);
		writer.close();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		String n1 = request.getParameter("num1");
		String n2 = request.getParameter("num2");
		String cal = request.getParameter("cal");
				
		try {
			int num1 = Integer.parseInt(n1);
			int num2 = Integer.parseInt(n2);
		}catch(Exception e) {
			writer.print("숫자가 아닙니다.");
			return;
		}
		
		doSuccess(request,response,n1,n2,cal);
		writer.close();
	}
}
