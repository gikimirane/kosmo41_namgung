package com.study.pattern04.factory_method1.factory;

import java.util.ArrayList;
import java.util.List;

import com.study.pattern04.factory_method1.unit.Unit;

//Creator 추상 클래스
public abstract class UnitGenerator {
	public abstract void createUnits();
	public List<Unit> units = new ArrayList<Unit>();
	
	public List<Unit> getUnit(){
		return units;
	}
}
