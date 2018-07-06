package com.study.pattern.factroy2;

public class Main {

	public static void main(String[] args) {
		
		Monster monster1 = MonsterFactory.createMonster(eMonster.Mon1);
		Monster monster2 = MonsterFactory.createMonster(eMonster.Mon2);
		
		monster1.move();
		monster1.eat();
		
		monster2.move();
		monster2.eat();
	}
}
