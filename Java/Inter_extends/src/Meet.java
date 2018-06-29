interface Greet {    //비어있는 추상메소드로 구성되어 있음
	void greet();
}
interface Bye extends Greet {    //비어 있는 추상메소드의 집합(interface)을 상속받음
	void bye();
}

class Greeting implements Bye {  //interface를 구현(Greet가 Bye를 상속받아 다 만들어야 함)
	public void greet() {
		System.out.println("안녕하세요!");
		//구현 안하면 안됨
	}
	public void bye() {
		System.out.println("안녕히 계세요!");
		//구현 안하면 안됨
	}
}

public class Meet {

	public static void main(String[] args) {
		
		Greeting greeting = new Greeting();     //모두 구현해서 new로 생성가능
		greeting.greet();
		greeting.bye();

	}

}
