package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class joinOk implements Service {

	public joinOk() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
				writer.println("<script language=\"javascript\">alert(\"회원가입을 축하합니다.\"); document.location.href=\"home_a.jsp\"; </script>");
				writer.close();
			}else {
				writer.println("<script language=\"javascript\">alert(\"회원가입을 실패했습니다.\"); history.back(); </script>");
				writer.close();
			}
		}
	}

}
