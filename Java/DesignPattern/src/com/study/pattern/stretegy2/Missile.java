package com.study.pattern.stretegy2;

public class Missile implements Weapon {
	@Override
	public void shoot() {
		System.out.println("미사일 공격!");
	}
}
