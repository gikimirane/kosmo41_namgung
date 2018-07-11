package com.study.pattern03.strategy2;

public class Main {

	public static void main(String[] args) {
		//캐릭터를 만들었어
		GameCharacter character = new GameCharacter();
		
		character.fire();
//		선택상황에 따라 전략적으로 무기를 선택한다!
		//화살 장착한거야~(무기가 없었거든)
		character.setWeapon(new Arrow());
		character.fire();
		
//		총알 장착한거야~
//		전략적으로 무기를 선택한다!
		character.setWeapon(new Bullet());
		character.fire();
		
		//무기 추가 미사일! interface가 같으므로 골라서 쓸 수 있음
		character.setWeapon(new Missile());
		character.fire();
		
//		동일한 목적 알고리즘을 선택적으로 적용함 (Arrow <> Bullet)
//		동일한 애들을 골라쓰는 것
	}
}

//전략패턴의 예 
//워드 문서에서 프린터, 폰트 사용 휙휙 갈아치움
//정수 배열에 대해 사하는 정렬알고리즘
//게임 캐릭터의 무기(교체 후) 사용
