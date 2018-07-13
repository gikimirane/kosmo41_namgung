package com.study.pattern04.factory_method1.unit;

//ConcreteProduct, 구체화된 것이라는 의미
public class Marine extends Unit {

	public Marine() {
		type = UnitType.Marine;
		name = "Marine";
		hp=100;
		exp=50;
		
		System.out.println(this.name+" 생성!!!");
	}
	@Override
	public void attack() {
		System.out.println(this.name+" 공격!!!");
	}
}
