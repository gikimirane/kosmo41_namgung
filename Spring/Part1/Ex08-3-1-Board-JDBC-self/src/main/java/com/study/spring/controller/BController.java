package com.study.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.spring.command.BCommand;
import com.study.spring.command.BContentCommand;
import com.study.spring.command.BDeleteCommand;
import com.study.spring.command.BListCommand;
import com.study.spring.command.BModifyCommand;

@Controller
public class BController {
	BCommand command;
	
	@RequestMapping("/list")
	public String list(Model model,HttpServletRequest Request) {
		command = new BListCommand();
		command.execute(model);
		return "list";
	}	
	
	@RequestMapping("/content_view")
	public String content_view(Model model,HttpServletRequest request) {
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model);
		return "content_view";
	}
	
	@RequestMapping("/delete")
	public String delete(Model model,HttpServletRequest request) {
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		return "redirect:list";
	}
	
	@RequestMapping("/modify_view")
	public String modifyView(Model model,HttpServletRequest request) {
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model);
		return "modify_view";
	}
	@RequestMapping("modify")
	public String modify(Model model,HttpServletRequest request) {
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);
		return "redirect:list";
	}
	
}
