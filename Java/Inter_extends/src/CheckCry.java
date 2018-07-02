interface Cry {
	void cry();
}

class Cat implements Cry{
	public void cry() {
		System.out.println("야옹~");
	}
}
class Dog {
	public void cry() {
		System.out.println("멍멍!");
	}
}

public class CheckCry {

	public static void main(String[] args) {
		
		Cat cat = new Cat();
		Dog dog = new Dog();
		
		if(cat instanceof Cry){   //interface를 구현한 관계
			cat.cry();
		}
		if(cat instanceof Cat){   //Cat 클래스의 인스턴스를 만든관계
			cat.cry();
		}
		if(dog instanceof Cry) {  //구현하지 않았기 때문에 False
			dog.cry();
		}
		

	}

}
