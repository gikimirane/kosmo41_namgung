package com.study.jsp1.command;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp1.BDao;
import com.study.jsp1.BDto;
import com.study.jsp1.BPageInfo;

public class BListCommand implements BCommand {

	public BListCommand() {
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int nPage = 1;
		HttpSession session = null;
		String search="";
		String input="";
		try {
			String sPage = request.getParameter("page");
			nPage = Integer.parseInt(sPage);
			
		}catch(Exception e) {}
		
		try {
			search = request.getParameter("search");
			input = request.getParameter("input");
			if(search==null) {
				search="0";
				input="0";
			}
		}catch(Exception e1) {
			search="0";
			input="0";
		}
		
		BDao dao = BDao.getInstance();
		BPageInfo pinfo = dao.articlePage(nPage);
		request.setAttribute("page", pinfo);
		
		//artclePage 로직을 통과하고나면 예외처리가 된 nPage가 나옴
		nPage = pinfo.getCurPage();
		
		
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		
		ArrayList<BDto> dtos = dao.list(nPage, search, input);
		request.setAttribute("list", dtos);
		

	}
}
