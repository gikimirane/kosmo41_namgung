package com.study.pattern.factory1;

public class Medic implements Unit {

	public Medic() {
		System.out.println("매딕 생성!!");
	}
	@Override
	public void move() {
		System.out.println("매딕 이동!!");

	}

}
