package com.study.jsp1;

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
}
