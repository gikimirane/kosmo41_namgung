package com.study.pattern04.strategy3;

public class MySQL extends Database {
	
	public MySQL() {
		name = "MySQL";
		rows = 20;
	}
	@Override
	public void connectDatabase() {
		System.out.println(name + "에 접속했습니다.");
	}
}
