package com.study.jsp1.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp1.BDao;
import com.study.jsp1.BDto;

public class BReplyViewCommand implements BCommand {

	public BReplyViewCommand() {
		
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String bId = request.getParameter("bId");
		BDao dao = BDao.getInstance();
		BDto dto = dao.reply_View(bId);
		
		request.setAttribute("reply_view", dto);
	}

}
