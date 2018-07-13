package com.study.pattern02.strategy2;

public class GameCharacter {
	//접근점이 된다(인터페이스가 각종 무기의 단일 통로가 되었음)
	private Weapon weapon;
	
	//무기 교환이 가능하게~ 구체적인 무기를 모두 weapon으로 받음
	public void setWeapon(Weapon weapon) {
		//무기를 파라미터로 넘겨주면 내가 set하겠다
		this.weapon = weapon;
	}
	
	
	//기능을 위임한 것, fire를 누르면 무기를 동작시켜주세요~ Delegate 함수 : shoot임
	public void fire() {
		if(weapon == null) {
			System.out.println("무기를 먼저 장착하세요.");
		}else {
			weapon.shoot();
			//구체적으로 모르지만 그냥 기능을 쓸꺼야
		}
	}	
}
