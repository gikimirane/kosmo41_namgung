package com.study.pattern.factroy2;

public class Mon2 implements Monster {

	public Mon2() {
		System.out.println("Mon2 생성했음!");
	}
	@Override
	public void move() {
		System.out.println("Mon2 움직여라!");
	}

	@Override
	public void eat() {
		System.out.println("Mon2 먹어라!");
	}

}
