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
		
		if(command.equals("/joinOk.do")) {
			joinOk(request,response);
		}else if(command.equals("/loginOk.do")) {
			loginOk(request,response);
		}else if(command.equals("/modifyOk.do")) {
			modifyOk(request,response);
		}else if(command.equals("/logout.do")) {
			logoutOk(request,response);
		}else if(command.equals("/delete.do")) {
			deleteOk(request,response);
		}
			
	}
	public void deleteOk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("deleteOk!");
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter writer = response.getWriter();
		
		MemberDto dto = new MemberDto();
		
		String id = (String)session.getAttribute("id");
		dto.setId(id);
		MemberDao dao = MemberDao.getInstance();
		int check = dao.deleteMember(dto);
		
		if(check==1) {
			session.invalidate();
			writer.println("<script language=\"javascript\">alert(\"회원 탈퇴가 완료되었습니다.\"); document.location.href=\"login.jsp\"; </script>");
			writer.close();
		}else {
			writer.println("<script language=\"javascript\">alert(\"회원 탈퇴에 실패했습니다.\"); history.go(-1); </script>");
			writer.close();
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
	public void loginOk (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("loginOK method");
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		MemberDao dao = MemberDao.getInstance();
		HttpSession session = request.getSession();
		PrintWriter writer = response.getWriter();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		int checkNum = dao.userCheck(id, pw);
		if(checkNum== -1){
			writer.println("<script language=\"javascript\">alert(\"아이디가 존재하지 않습니다.\"); history.go(-1);</script>");
		}else if(checkNum==0) {
			writer.println("<script language=\"javascript\">alert(\"비밀번호가 틀립니다.\"); history.go(-1); </script>");
		}else if(checkNum==1) {
			MemberDto dto = dao.getMember(id);
			if(dto==null) {
				writer.println("<script language=\"javascript\">alert(\"존재하지 않는 회원입니다.\"); history.go(-1); </script>");
				
				
			}else {
				String name = dto.getName();
				session.setAttribute("id",id);
				session.setAttribute("name",name);
				session.setAttribute("ValidMem","yes");
				response.sendRedirect("main.jsp");
			}
		}
		
		writer.close();
	}
	public void modifyOk (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		System.out.println("modifyOk method");
		MemberDto dto = new MemberDto();
		HttpSession session = request.getSession();
		PrintWriter writer = response.getWriter();
		
		String id = (String)session.getAttribute("id");
		String eMail = request.getParameter("eMail");
		String pw = request.getParameter("pw");
		String address =request.getParameter("address");
				
		dto.setId(id);
		dto.setAddress(address);
		dto.setPw(pw);
		dto.seteMail(eMail);
		
		MemberDao dao = MemberDao.getInstance();
		int ri= dao.updateMember(dto);
		if(ri==1){
			writer.println("<script language=\"javascript\">alert(\"정보가 수정되었습니다.\"); document.location.href=\"main.jsp\"; </script>");
			writer.close();
		}else {
			writer.println("<script language=\"javascript\">alert(\"정보수정에 실패했습니다.\"); history.go(-1); </script>");
			writer.close();
		}
	}
	public void joinOk (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		MemberDto dto = new MemberDto();
		
		HttpSession session = request.getSession();
		PrintWriter writer = response.getWriter();
				
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String eMail = request.getParameter("eMail");
		String pw = request.getParameter("pw");
		String address =request.getParameter("address");
		
		dto.setAddress(address);
		dto.seteMail(eMail);
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		
		System.out.println(dto.getAddress());
		System.out.println(dto.geteMail());
		System.out.println(dto.getPw());
		System.out.println(dto.getId());
		System.out.println(dto.getName());
		
		
		MemberDao dao = MemberDao.getInstance();
		if(dao.confirmId(dto.getId())==MemberDao.MEMBER_EXISTENT){
			writer.println("<script language=\"javascript\">alert(\"아이디가 이미 존재합니다.\"); history.back(); </script>");
			writer.close();
		}else {
			int ri = dao.insertMember(dto);
			if(ri ==MemberDao.MEMBER_JOIN_SUCCESS){
				session.setAttribute("id",dto.getId());
				writer.println("<script language=\"javascript\">alert(\"회원가입을 축하합니다.\"); document.location.href=\"login.jsp\"; </script>");
				writer.close();
			}else {
				writer.println("<script language=\"javascript\">alert(\"회원가입을 실패했습니다.\"); document.location.href=\"login.jsp\"; </script>");
				writer.close();
			}
		}
	}
}
