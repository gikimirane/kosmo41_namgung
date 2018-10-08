package com.study.android;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AndroidController
 */
@WebServlet("*.ad")
public class AndroidController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}
	
	public void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com=uri.substring(conPath.length());
		System.out.println("com은?"+com);
		ACommand command;
		String viewPage="";
		if(com.equals("/adminlogin.ad")) {
			command = new AAdminLoginCommand();
			command.execute(request, response);
			viewPage = "adminpage.jsp";
		}
		//얘를 통해서 forward 시켜버림, forward할 때 request랑 response 데려가~
		RequestDispatcher dispatcher = request.getRequestDispatcher("adminpage.jsp");
		dispatcher.forward(request,response);
	}

}
