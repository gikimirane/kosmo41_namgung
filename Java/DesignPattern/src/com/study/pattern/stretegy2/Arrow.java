package com.study.pattern.stretegy2;
//구현해서 화살공격을 만듬
public class Arrow implements Weapon {

	@Override
	public void shoot() {
		System.out.println("화살 공격!");
	}

}
