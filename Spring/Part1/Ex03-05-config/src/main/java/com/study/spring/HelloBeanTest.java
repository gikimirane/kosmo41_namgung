package com.study.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloBeanTest {
	
	public static void main(String[] args) {
		//1.스프링 설정이 클래스 패스 루트가 아닌 다른곳에 위치한다
		//	루트를 기준으로 경로 형식을 입력
		//String configLocation = "classpath:config/beans.xml";
		
		//2. 클래스패스가 아닌 파일 시스템에서 설정 파일 읽어 오기
		//String configLocation = "file:src/main/resources/config/beans2.xml";
		
		//3. 특정 경로에 있는 모든 xml 파일을 설정파일로 사용하고 싶은 경우
		//String configLocation = "classpath:config/beans*.xml";
		
		//4. 1개 이상의 설정 파일 경로를 값으로 전달 가능 : 가변인자 형태
		//
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext("classpath:beans.xml",
													"classpath:config/beans2.xml");
		
		//1. IoC 컨테이너 생성 (자식을 부모한테 대입)
		//어차피 읽고난 뒤에 하나로 합치기 때문에 어디에 있건 다 모아서 부르면 된다.
		//ApplicationContext context = 
		//		new GenericXmlApplicationContext(configLocation);
		
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
