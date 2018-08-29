package com.study.jsp1.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp1.BDao;

public class BModifyCommand implements BCommand {

	public BModifyCommand() {
		
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String bName=request.getParameter("bName");
		String bTitle=request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bId = request.getParameter("bId");
		
		BDao dao = BDao.getInstance();
		dao.modify(bName,bTitle,bContent,bId);
	}

}
