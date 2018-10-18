package com.study.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Student implements InitializingBean, DisposableBean{
	
	private String name;
	private int age;
	
	
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	
	public void destroy() {
		System.out.println("Student : destroy()");
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("Student : AfterPropertiesSet");
		
	}
}
