interface Animal {
	void cry();
}

class Cat2 implements Animal{
	public void cry() {
		System.out.println("야옹~");
	}
}
class Dog2 implements Animal {
	public void cry() {
		System.out.println("멍멍!");
	}
}
public class CheckCry2 {

	public static void main(String[] args) {
		Animal cat = new Cat2();
		Animal dog = new Dog2();
		checkAnimal(cat);
	//	checkAnimal(dog);
	}
	
	public static void checkAnimal(Animal animal) {  //받는 값을 이런것도 되는구나..
		if(animal instanceof Cat2) {
			System.out.println("고양이");
			animal.cry();
		}
		else if(animal instanceof Dog2) {
			System.out.println("강아지");
			animal.cry();
		}
	}
}
