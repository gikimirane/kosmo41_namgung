package animal;

public class Dog {
	public void welcome(zoo.Cat c) {
		//c.makeSound();  //호출 가능! public이라서
		//c.makeHappy(); //Public이 아니므로 타 패키지 > 클래스 > 메소드 사용 불가
	}
}	
