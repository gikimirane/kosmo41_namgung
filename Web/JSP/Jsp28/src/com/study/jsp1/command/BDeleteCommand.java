package com.study.jsp1.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp1.BDao;

public class BDeleteCommand implements BCommand {

	public BDeleteCommand() {
		
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String bId=request.getParameter("bId");
				
		BDao dao = BDao.getInstance();
		dao.delete(bId);
	}

}
