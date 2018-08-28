package com.study.jsp1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.do")
public class FrontCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		actionDo(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request,response);
	}
	public void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		String uri = request.getRequestURI();
		System.out.println("url : "+uri);
		String conPath = request.getContextPath();
		System.out.println("conPath : "+conPath);
		String command=uri.substring(conPath.length());
		System.out.println("command : "+command);
				
		System.out.println("actionDo");
		Service service =null;
		if(command.equals("/joinOk.do")) {
			service = new joinOk();
			service.execute(request, response);
		}else if(command.equals("/loginOk.do")) {
			service = new loginOk();
			service.execute(request, response);
		}else if(command.equals("/modifyOk.do")) {
			service = new modifyOk();
			service.execute(request, response);
		}else if(command.equals("/logout.do")) {
			logoutOk(request,response);
		}else if(command.equals("/delete.do")) {
			service = new deleteOk();
			service.execute(request, response);
		}
	}
	
	public void logoutOk (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("logoutOk!");
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter writer = response.getWriter();
		
		session.invalidate();
		writer.println("<script language=\"javascript\">alert(\"로그아웃이 완료되었습니다.\"); document.location.href=\"login.jsp\"; </script>");
		writer.close();
	}
	
}
