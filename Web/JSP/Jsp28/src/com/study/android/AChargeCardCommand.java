package com.study.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AChargeCardCommand implements ACommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String code = request.getParameter("code");
		ADao dao = ADao.getInstance();
		ArrayList<userDTO> list = new ArrayList<>();
		
		list=dao.chargeCard();
		request.setAttribute("userlist", list);
		
		String viewPage = "userchargeCard.jsp";
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request,response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
