package com.study.spring;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		List<String> mylist = hello.getNames();
		for(String value : mylist) {
			System.out.println("List : "+value);
		}
		
		Set<Integer> myset1 = hello.getNums();
		for(int value : myset1) {
			System.out.println("set : "+value);
		}
		
		Map<String,Integer> map = hello.getAges();
		
		// 방법1 : entrySet
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("Map1 : "+entry.getKey()+" - "+entry.getValue());
		}
		
		// 방법2 : KeySet
		for(String key : map.keySet()) {
			Integer value = map.get(key);
			System.out.println("Map2 : "+key+" - "+value);
		}
		
		// 방법3 : Iterator
		Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			Integer value = map.get(key);
			System.out.println("Map3 : "+key+" - "+value);
		}
	}
}
