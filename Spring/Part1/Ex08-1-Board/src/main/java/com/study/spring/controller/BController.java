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
import com.study.spring.command.BReplyCommand;
import com.study.spring.command.BReplyViewCommand;
import com.study.spring.command.BWriteCommand;

@Controller
public class BController {
	BCommand command;
	
	@RequestMapping("/list")
	public String list(Model model) {
		command = new BListCommand();
		command.execute(model);
		return "list"; 
	}
	
	@RequestMapping("/content_view")
	public String contentView(Model model,HttpServletRequest request) {
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model);
		return "content_view";
	}
	@RequestMapping("/modify_view")
	public String modifyView(Model model,HttpServletRequest request) {
		
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model);
		return "modify_view";
	}
	
	@RequestMapping("/modify")
	public String modify(Model model,HttpServletRequest request) {
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);
		return "redirect:list";
	}
	
	@RequestMapping("/write_view")
	public String writeView(Model model,HttpServletRequest request) {
				
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(Model model,HttpServletRequest request) {
		System.out.println("write()");
		model.addAttribute("request", request);
		command = new BWriteCommand();
		command.execute(model);
		return "redirect:list";
	}
	@RequestMapping("/delete")
	public String delete(Model model,HttpServletRequest request) {
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String replyView(Model model,HttpServletRequest request) {
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(Model model,HttpServletRequest request) {
		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.execute(model);
		return "redirect:list";
	}
}
