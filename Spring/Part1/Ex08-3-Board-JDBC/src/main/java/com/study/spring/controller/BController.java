package com.study.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.spring.command.BCommand;
import com.study.spring.command.BListCommand;
import com.study.spring.util.Constant;

@Controller
public class BController {
	BCommand command;
	
	@Autowired
	private ApplicationContext context;
	public JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}
		
	@RequestMapping("/list")
	public String list(Model model) {
//		command = new BListCommand();
		command = context.getBean(BListCommand.class);
		command.execute(model);
		return "list"; 
	}
	
	@RequestMapping("/content_view")
	public String contentView(Model model,HttpServletRequest request) {
		model.addAttribute("request", request);
//		command = new BContentCommand();
		command = (BCommand) context.getBean("contentHandler");
		command.execute(model);
		return "content_view";
	}
	@RequestMapping("/modify_view")
	public String modifyView(Model model,HttpServletRequest request) {
		
		model.addAttribute("request", request);
//		command = new BContentCommand();
		command = (BCommand) context.getBean("contentHandler");
		command.execute(model);
		return "modify_view";
	}
	
	@RequestMapping("/modify")
	public String modify(Model model,HttpServletRequest request) {
		model.addAttribute("request", request);
//		command = new BModifyCommand();
		command = (BCommand) context.getBean("modifyHandler");
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
//		command = new BWriteCommand();
		command = (BCommand) context.getBean("writeHandler");
		command.execute(model);
		return "redirect:list";
	}
	@RequestMapping("/delete")
	public String delete(Model model,HttpServletRequest request) {
		model.addAttribute("request", request);
//		command = new BDeleteCommand();
		command = (BCommand) context.getBean("deleteHandler");
		command.execute(model);
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String replyView(Model model,HttpServletRequest request) {
		model.addAttribute("request", request);
//		command = new BReplyViewCommand();
		command = (BCommand) context.getBean("replyViewHandler");
		command.execute(model);
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(Model model,HttpServletRequest request) {
		model.addAttribute("request", request);
//		command = new BReplyCommand();
		command = (BCommand) context.getBean("replyHandler");
		command.execute(model);
		return "redirect:list";
	}
}
