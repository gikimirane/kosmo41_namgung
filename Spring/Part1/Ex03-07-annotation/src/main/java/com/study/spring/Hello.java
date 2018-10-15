package com.study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Hello {
	
	@Value("홍길동")
	private String name;
	@Value("전우치")
	private String nickname;
	@Autowired
	@Qualifier("printerA")
	private Printer printer;
	
	//생성할 때 쓰는 Setter 메소드는 없어도 된다
	/*public void setName(String name) {
		this.name = name;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}*/
	
	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	
	public String sayHello() {
		return "Hello "+name+" : "+nickname;
	}
	public void print() {
		printer.print(sayHello());
	}
}
