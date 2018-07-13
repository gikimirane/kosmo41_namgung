package com.study.pattern04.factory_method2.factory;

import com.study.pattern04.factory_method2.database.Database;
import com.study.pattern04.factory_method2.database.Informix;
import com.study.pattern04.factory_method2.database.MySQL;
import com.study.pattern04.factory_method2.database.Oracle;

public class DatabaseFactoryUse extends DatabaseFactory {
	
	public DBType dbType = DBType.Oracle;
	@Override
	public Database setDatabase() {
		//회사 사정에 의해 어떤 데이터 베이스를 사용할지는 모른다.
		//그래서 구축한 정보를 지우지 않고 재사용 시를 대비한다.
		if(dbType==DBType.MySQL) {
			System.out.println("MySQL use...");
			return new MySQL();
		}else if(dbType==DBType.Oracle) {
			System.out.println("Oracle use...");
			return new Oracle();
		}else if(dbType==DBType.Informix) {
			System.out.println("Informix use...");
			return new Informix();
		}
		return null;
	}

}
