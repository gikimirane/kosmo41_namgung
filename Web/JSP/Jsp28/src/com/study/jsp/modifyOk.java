package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class modifyOk implements Service {

	public modifyOk() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

}
