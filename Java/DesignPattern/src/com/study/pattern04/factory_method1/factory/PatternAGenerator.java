package com.study.pattern04.factory_method1.factory;

import com.study.pattern04.factory_method1.unit.Marine;

// A타입으로 ConcreteCreator(구체적으로 만드는) 클래스
public class PatternAGenerator extends UnitGenerator {

	@Override
	public void createUnits() {
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());
	}

}
