package com.study.pattern02.strategy3;

public abstract class Database {
	public String name;
	public double rows;
	
//	DB마다 접속방식이 다르다! 그래서 추상메소드로 선언만 함
	public abstract void connectDatabase();
}
