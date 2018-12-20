package com.study.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private String mykey="";
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping("/main")
	public String main(Model model) {
		APIExamCaptchaNkey key = new APIExamCaptchaNkey();
		mykey =key.getKey();
		model.addAttribute("key",mykey);
		//key.checkNumber();
		return "main";
	}
	
	@RequestMapping("/checkNumber")
	@ResponseBody
	public JSONObject getimg(HttpServletRequest request) throws ParseException {
		
		APIExamCaptchaNkey key = new APIExamCaptchaNkey();
		String value = request.getParameter("value");
		String result=key.checkNumber(mykey,value);
		
		JSONParser jsonParser = new JSONParser();
        JSONObject obj = new JSONObject();
        obj = (JSONObject) jsonParser.parse(result);
       
		return obj;
	}
}
