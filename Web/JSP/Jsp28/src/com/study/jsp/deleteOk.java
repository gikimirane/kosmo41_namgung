package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class deleteOk implements Service {

	public deleteOk() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

}
