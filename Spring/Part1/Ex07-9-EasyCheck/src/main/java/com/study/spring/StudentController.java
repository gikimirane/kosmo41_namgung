package com.study.spring;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

	@RequestMapping("/studentForm")
	public String studentForm() {
		return "createPage";
	}
	
	@RequestMapping("/student/create")
	public String studentCreate(@ModelAttribute("student")Student student,
								BindingResult result) {
		
		String page= "createDonePage";
		String studentName = student.getName();
		if((studentName==null) || studentName.trim().isEmpty()){
			System.out.println("studentName is null or empty");
			page="createPage";
		}
		
		int studentId = student.getId();
		if(studentId==0) {
			System.out.println("studentId is 0");
			page="createPage";
		}
		
		return page;
	}
}
