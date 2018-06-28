class Animal {
	String name;
	int age;
	String color;
	
	void printPet() {
		System.out.println("이름 : "+name);
		System.out.println("나이 : "+age);
		System.out.println("색상 : "+color);
	}
}
class Cat extends Animal {
	String variety;
	
	void printPet() { //부모한테 있는 메소드명과 동일하게 하는 것 : 오버라이딩
		super.printPet(); 
		//오버라이딩할 때 부모꺼+자식이 수정한 거 같이 쓰려면 메소드 앞에 "super."를 쓰면 됨 
		//만약 super.printPet()이 없다면 아래 자식이 넣은거만 찍히게 됨 (printPet())
		System.out.println("종류 : "+variety); //자식이 수정한 부분
	}
}
public class pet {
	public static void main(String[] args) {
		Cat cat = new Cat();
		cat.name = "양순이";
		cat.age = 5;
		cat.variety = "페르시안";
		cat.color = "갈색";
		cat.printPet();    //이걸 부르면 부모꺼메소드+자식이 수정한거 같이 나옴
	}
}
