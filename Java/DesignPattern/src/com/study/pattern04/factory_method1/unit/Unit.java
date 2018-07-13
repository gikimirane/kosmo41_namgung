package com.study.pattern04.factory_method1.unit;

enum UnitType{
	Marine,
	Firebat
}

//제품에 대한 추상클래스
public abstract class Unit {
	protected UnitType type;
	protected String name;
	protected int hp;
	protected int exp;
//	protected는 상속한 애들만 보여라 
	public abstract void attack();
	
}
