package com.study.pattern04.factory_method1;

import java.util.List;

import com.study.pattern04.factory_method1.factory.PatternAGenerator;
import com.study.pattern04.factory_method1.factory.PatternBGenerator;
import com.study.pattern04.factory_method1.factory.UnitGenerator;
import com.study.pattern04.factory_method1.unit.Unit;

public class Main {

	public static void main(String[] args) {
		// 타입이 두가지 있다는 것만 알고 있음
		// 공장만 알고 있음
		
		UnitGenerator[] unitGenerators = new UnitGenerator[2];
		unitGenerators[0] = new PatternAGenerator();
		unitGenerators[1] = new PatternBGenerator();
		
//		DoMakeType(unitGenerators[0]);
		DoMakeType(unitGenerators[1]);

	}
	public static void DoMakeType(UnitGenerator ug) {
//		무엇이 만들어질지는 모른다.
		ug.createUnits(); // 만들어줘 라고 함
		List<Unit> units = ug.getUnit(); //만든거 여기에 넣어줘
		
		for(Unit unit : units) {
			unit.attack();
		}
	}
}
