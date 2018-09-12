package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginOk implements Service {

	public loginOk() {
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("loginOK method");
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		MemberDao dao = MemberDao.getInstance();
		
		PrintWriter writer = response.getWriter();
		String id=null;
		String pw=null;
		String type=null;
		try {
			type = request.getParameter("type");
			id = request.getParameter("id1");
			pw = request.getParameter("pw");
			
		}catch(Exception e) {}
		
		if(type.equals("google")) {
			String name= request.getParameter("name");
			session.setAttribute("logintype", type);
			session.setAttribute("name", name);
			session.setAttribute("ValidMem","yes");
			System.out.println("로그인ok에 name : "+name);
			System.out.println("로그인ok에 type : "+type);
			
			response.sendRedirect("./main.jsp");
			return;
		}else if(type.equals("facebook")) {
			String name = request.getParameter("name");
			session.setAttribute("logintype", type);
			session.setAttribute("name", name);
			session.setAttribute("ValidMem","yes");
			System.out.println("로그인ok에 name : "+name);
			System.out.println("로그인ok에 type : "+type);
			response.sendRedirect("./main.jsp");
			return;
		}else if(type.equals("naver")) {
			String name=request.getParameter("name");
			session.setAttribute("logintype", type);
			session.setAttribute("name", name);
			session.setAttribute("ValidMem","yes");
			System.out.println("로그인ok에 name : "+name);
			System.out.println("로그인ok에 type : "+type);
			response.sendRedirect("./main.jsp");
			return;
		}
						
		int checkNum = dao.userCheck(id, pw);
		System.out.println("checkNum?"+checkNum);
		System.out.println("id : "+id);
		if(checkNum== -1){
			writer.println("<script language=\"javascript\">alert(\"아이디가 존재하지 않습니다.\"); history.go(-1);</script>");
		}else if(checkNum==0) {
			writer.println("<script language=\"javascript\">alert(\"비밀번호가 틀립니다.\"); history.go(-1);</script>");
		}else if(checkNum==1) {
			MemberDto dto = dao.getMember(id);
		
			if(dto==null) {
				writer.println("<script language=\"javascript\">alert(\"존재하지 않는 회원입니다.\"); history.go(-1); </script>");
			}else {
				
				String name = dto.getName();
				session.setAttribute("logintype", "none");
				session.setAttribute("id",id);
				session.setAttribute("name",name);
				session.setAttribute("ValidMem","yes");
				response.sendRedirect("./main.jsp");
				return;
			}
		}
		writer.close();
	}
}
