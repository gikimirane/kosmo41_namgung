package com.study.pattern03.strategy2;

public class Bullet implements Weapon {
//화살과 총알의 공통분모는 Weapon 인터페이스
	@Override
	public void shoot() {
		System.out.println("총알 공격!");
	}

}
