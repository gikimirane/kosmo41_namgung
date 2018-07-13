package com.study.pattern02.strategy3;

public class Infomix extends Database {
	public Infomix() {
		name = "Infomix";
		rows = 40;
	}
	@Override
	public void connectDatabase() {
		System.out.println(name + "에 접속했습니다.");
	}
}
