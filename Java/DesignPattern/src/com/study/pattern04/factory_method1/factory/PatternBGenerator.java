package com.study.pattern04.factory_method1.factory;

import com.study.pattern04.factory_method1.unit.Firebat;
import com.study.pattern04.factory_method1.unit.Marine;

//B타입으로 ConcreteCreator(구체적으로 만드는) 클래스
public class PatternBGenerator extends UnitGenerator {
//A공장은 마린만 8개, B공장은 Firebat, Marine 각 4개씩
	@Override
	public void createUnits() {
		units.add(new Firebat());
		units.add(new Firebat());
		units.add(new Firebat());
		units.add(new Firebat());
		
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());
	}
}
