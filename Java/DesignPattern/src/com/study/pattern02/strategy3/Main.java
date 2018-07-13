package com.study.pattern02.strategy3;

public class Main {

	public static void main(String[] args) {
//		데이터 베이스를 전략적으로 선택하여 사용한다 (나의 목적)
		DatabaseUse myDB = new DatabaseUse();
		myDB.connect();   //지금은 없고
		
		myDB.setDatabase(new MySQL());   //이제는 넣었어
		myDB.connect();
		
		myDB.setDatabase(new Oracle());
		myDB.connect();
		
		myDB.setDatabase(new Infomix());
		myDB.connect();
	}
}
