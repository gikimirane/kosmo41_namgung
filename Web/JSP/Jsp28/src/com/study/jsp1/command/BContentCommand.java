package com.study.jsp1.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp1.BDao;
import com.study.jsp1.BDto;

public class BContentCommand implements BCommand {

	public BContentCommand() {
		
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		String bId = request.getParameter("bId");
		BDao dao = BDao.getInstance();
		BDto dto = dao.contentView(bId);
		
		session.setAttribute("content_view", dto);
	}

}
