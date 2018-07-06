package com.study.pattern.factroy2;

public class Mon1 implements Monster {


	public Mon1() {
		System.out.println("Mon1 생성했음!");
	}
	public void move() {
		System.out.println("Mon1 움직여라!");

	}
	@Override
	public void eat() {
		System.out.println("Mon1 먹어라!");
	}

}
