package com.study.pattern04.factory_method2.database;

// Product의 추상클래스

public abstract class Database {
	public String name;
	public double rows;
	
	public abstract void connectDatabase();
	
//	데이터 베이스 마다 접속방식은 다 다르다
	public void insertData() {
		System.out.println(name+"에 데이터를 추가 했습니다.");
	}
//	업무 처리 방식이 같다
	public void selectData() {
		System.out.println(name + "에서 데이터를 "+rows+"개 조회했습니다.");
	}
}
