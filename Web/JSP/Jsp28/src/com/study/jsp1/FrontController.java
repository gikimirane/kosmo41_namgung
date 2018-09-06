package com.study.jsp1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.Service;
import com.study.jsp.*;
import com.study.jsp1.command.*;
import com.study.jsp1.*;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public FrontController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
		actionDo(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		actionDo(request,response);
	}
	public void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String viewPage="home_a.jsp";
		BCommand command =null;
		Service service =null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com=uri.substring(conPath.length());
		
		HttpSession session =null;
		session = request.getSession();
		int curPage = 1;
		if(session.getAttribute("cpage")!=null) {
			curPage =(int)session.getAttribute("cpage");
		}	
		
		if(com.equals("/write_view.do")) {
			viewPage="write_view.jsp";
		}else if(com.equals("/write.do")) {
			
			command = new BWriteCommand();
			command.execute(request,response);
			System.out.println("여기는?");
			viewPage = "list.do";
			System.out.println("viewPage?"+viewPage);
		}else if(com.equals("/list.do")) {
			command = new BListCommand();
			command.execute(request,response);
			viewPage="list.jsp";
		}else if(com.equals("/content_view.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			viewPage="content_view.jsp";
		}else if(com.equals("/modify_view.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			viewPage="modify_view.jsp";
		}else if(com.equals("/modify.do")) {
			command = new BModifyCommand();
			command.execute(request, response);
			
			command = new BContentCommand();
			command.execute(request, response);
			viewPage="/content_view.jsp";
		}else if(com.equals("/delete.do")) {
			command = new BDeleteCommand();
			command.execute(request, response);
			viewPage="list.do?page="+curPage;
		}else if(com.equals("/reply_view.do")) {
			command = new BReplyViewCommand();
			command.execute(request, response);
			viewPage="reply_view.jsp";
		}else if(com.equals("/reply.do")) {
			command = new BReplyCommand();
			command.execute(request,response);
			viewPage="list.do?page="+curPage;
		}else if(com.equals("/joinOk.do")) {
			service = new joinOk();
			service.execute(request, response);
			return;
		}else if(com.equals("/loginOk.do")) {
			service = new loginOk();
			service.execute(request, response);
			return;		
		}else if(com.equals("/modifyOk.do")) {
			service = new modifyOk();
			service.execute(request, response);
			
		}else if(com.equals("/logout.do")) {
			logoutOk(request,response);
			
		}else if(com.equals("/delete.do")) {
			service = new deleteOk();
			service.execute(request, response);
		}else if(com.equals("/duplication.do")){
			service = new duplication();
			service.execute(request, response);
			return;
		}else if(com.equals("/download.do")) {
			command = new BDownloadFile();
			command.execute(request, response);
			return;
		}else if(com.equals("/galleryList.do")) {
			command = new BGalleryCommand();
			command.execute(request, response);
			System.out.println("갤러리두 완료");
			viewPage="galleryList.jsp";
		}
	
		//얘를 통해서 forward 시켜버림, forward할 때 request랑 response 데려가~
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request,response);
	}
	public void logoutOk (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("logoutOk!");
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter writer = response.getWriter();
		
		session.invalidate();
		writer.println("<script language=\"javascript\">alert(\"로그아웃이 완료되었습니다.\"); document.location.href=\"home_a.jsp\"; </script>");
		writer.close();
	}
}
