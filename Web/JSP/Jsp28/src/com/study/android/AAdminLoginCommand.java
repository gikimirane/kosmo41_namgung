package com.study.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AAdminLoginCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		ArrayList<ADto> list = new ArrayList<>();
		
		String inputId = request.getParameter("id");
		String inputPw=request.getParameter("pw");
		ADao dao = ADao.getInstance();
		list = dao.adminlist();
		
		request.setAttribute("list", list);
		
	}
}
