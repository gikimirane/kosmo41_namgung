package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp1.BDao;

public class duplication implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = response.getWriter();
		MemberDao dao = MemberDao.getInstance();
		String id = request.getParameter("id");
		System.out.println("id : "+id);
		if(id.isEmpty() || id==null || id.equals("")) {
			writer.println("<script language=\"javascript\">alert(\"아이디를 입력하세요.\"); history.back(-1);</script>");		
			return;
		}
		
		int check = dao.confirmId(id);
		System.out.println("check : "+check);
		if(check==0) {
			writer.println("<script language=\"javascript\">alert(\"아이디 사용이 가능합니다.\"); history.go(-1);</script>");
		}else {
			writer.println("<script language=\"javascript\">alert(\"중복된 아이디 입니다.\"); history.go(-1);</script>");
		}
	}
}
