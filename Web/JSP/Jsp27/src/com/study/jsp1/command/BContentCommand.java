package com.study.jsp1.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp1.BDao;
import com.study.jsp1.BDto;

public class BContentCommand implements BCommand {

	public BContentCommand() {
		
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String bId = request.getParameter("bId");
		BDao dao = BDao.getInstance();
		BDto dto = dao.contentView(bId);
		
		request.setAttribute("content_view", dto);
	}

}
