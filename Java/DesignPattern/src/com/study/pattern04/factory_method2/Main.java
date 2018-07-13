package com.study.pattern04.factory_method2;

import com.study.pattern04.factory_method2.database.Database;
import com.study.pattern04.factory_method2.factory.DatabaseFactory;
import com.study.pattern04.factory_method2.factory.DatabaseFactoryUse;

public class Main {

	public static void main(String[] args) {
		DatabaseFactory factory = new DatabaseFactoryUse();
//		어떤 데이터 베이스가 사용될지는 모른다
//		팩토리 메서드에서 제공되는 메소드를 그냥 사용한다.
		
		Database db = factory.setDatabase();
//		DB에 접속
		db.connectDatabase();
		
//		접속된 데이터베이스를 이용해서 업무처리를 한다
		db.insertData();
		db.selectData();
	}
}
