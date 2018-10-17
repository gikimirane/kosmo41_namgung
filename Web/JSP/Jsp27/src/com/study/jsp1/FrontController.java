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

import com.study.jsp1.command.*;

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
		
		String viewPage=null;
		BCommand command =null;
		
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
			viewPage = "list.do?page="+curPage;
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
		}
			
		//얘를 통해서 forward 시켜버림, forward할 때 request랑 response 데려가~
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request,response);
		
		//write.do하면은 결국 다시 controller로 들어와서 다시 /list.do를 타게 됨
	}	
}
