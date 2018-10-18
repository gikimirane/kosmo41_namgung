package com.study.spring.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.study.spring.BDao.BDao;

public class BModifyCommand implements BCommand {

	public BModifyCommand() {
		
	}
	@Override
	public void execute(Model model) {
		Map<String,Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String bName=request.getParameter("bName");
		String bTitle=request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bId = request.getParameter("bId");
		
		BDao dao = BDao.getInstance();
		dao.modify(bName,bTitle,bContent,bId);
	}

}
