abstract class Unit {
	abstract void attack();    //얘는 메소드야 생성자 아니야 왜냐면 클래스명이랑 달라서 그리고 리턴타입도 있잖아 그래서 메소드인거야
}

class Marine extends Unit{
	public void attack() {
	System.out.println("총으로 공격");
	System.out.println("공격력 10으로 공격");
	}
}

class Zealot extends Unit {
	public void attack() {
		System.out.println("손으로 공격");
		System.out.println("공격력 8로 공격");
	}
}

class Zergling extends Unit {
	public void attack() {
		System.out.println("입으로 공격");
		System.out.println("공격력 9로 공격");
	}
	void eat() {
		
	}
}

public class UnitUse {

	public static void main(String[] args) {
		//테란
		//엄마가 unit1라는 애를 만들었는데(new), 메소드는 Marine(자식)한테 있는걸 쓸 수 있게 한거야
		Unit unit1 = new Marine();
		unit1.attack();
		
		//프로토스
		//엄마가 unit2라는 애를 만들었는데, 메소드는 내꺼는 구리니까 Zealot(자식)한테 있는거를 쓸 수 있게 생성한거야
		Unit unit2 = new Zealot();
		unit2.attack();
		
		//저그
		//엄마가 unit3 이라는 애를 만들었는데 메소드는 내꺼는 비었으니까 Zergling에 있는거 가따 쓸꺼야
		//왜냐면 내가 unit3한테 바라는건 Zergling한테 있거든 굳이 안만들구 자식꺼 빌려? 오는거야
		Unit unit3 = new Zergling();
		unit3.attack();

	}

}
