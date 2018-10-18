package com.study.spring.command;
import java.util.ArrayList;

import org.springframework.ui.Model;

import com.study.spring.BDao.BDao;
import com.study.spring.BDto.BDto;

public class BListCommand implements BCommand {

	public BListCommand() {
	}
	@Override
	public void execute(Model model) {
		
		BDao dao = BDao.getInstance();	
		ArrayList<BDto> dtos = dao.list();
		model.addAttribute("list", dtos);
		
	}
}
