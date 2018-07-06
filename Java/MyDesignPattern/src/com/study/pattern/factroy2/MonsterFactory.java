package com.study.pattern.factroy2;

enum eMonster{
	Mon1,Mon2;
}

public class MonsterFactory{
	
	public static Monster createMonster(eMonster type) {
		Monster mon = null;
		
		switch(type) {
		case Mon1 : 
			mon = new Mon1();
			break;
		case Mon2 :
			mon = new Mon2();
		}
		return mon;
	}
	
}
