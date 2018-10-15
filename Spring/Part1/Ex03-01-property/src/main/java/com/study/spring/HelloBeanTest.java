package com.study.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloBeanTest {
	
	public static void main(String[] args) {
		String configLocation = "classpath:beans.xml";
		
		//1. IoC 컨테이너 생성 (자식을 부모한테 대입)
		//ApplicationContext는 Close가 없다
		ApplicationContext context = 
				new GenericXmlApplicationContext(configLocation);
		
		//2. Hello bean 가져오기
		Hello hello = (Hello)context.getBean("hello");
		hello.print();
		
		//3. PrinterB Bean 가져오기
		Printer printer = context.getBean("printerB",Printer.class);
		hello.setPrinter(printer);
		hello.print();
		
		//context.close();
	}
}
