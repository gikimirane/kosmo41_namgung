package com.study.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloBeanTest {
	
	public static void main(String[] args) {
		
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(Config.class);
		
		//2. Hello bean 가져오기
		Hello helloA = (Hello)context.getBean("hello");
		helloA.print();
		
		Hello helloB = (Hello)context.getBean("hello1");
		helloB.print();
				
		//3. PrinterB Bean 가져오기
		Printer printer = context.getBean("printerB",Printer.class);
		helloA.setPrinter(printer);
		helloA.print();
		
		//싱글톤 확인
		System.out.println(helloA==helloB);
	}
}