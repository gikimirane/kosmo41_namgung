package com.study.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Student {
	
	private String name;
	private int age;
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}
	
	public void getStudentInfo() {
		System.out.println("이름 : "+getName());
		System.out.println("나이 : "+getAge());
		
		try {
			System.out.println(10/0);
		}catch(Exception e) {}
	}
	
	
	
}
