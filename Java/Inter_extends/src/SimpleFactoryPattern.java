//자식 객체를 부모 객체에 대입하기 (* 꼭 이해하자) 뭔지 머르게떠!!!!!!!!!!!!!!
//부모클래스의 안이 비어있어(추상클래스), 다른건 자식한테 안부러운데 내가 물려준 메소드(추상클래스)를 고쳐쓴건 부러워 그것만 쓸래
abstract class Human {
	abstract void print();  //추상메소드
}
class Man extends Human {
	String str;
	
	Man(String str){
		this.str = str;
	}
	public void print() {
		System.out.println(str + " 생성1");
		System.out.println(str + " 생성2");
	}
}
class Woman extends Human {
	String str;
	Woman(String str){
		this.str = str;
	}
	public void print() {
		System.out.println(str + " 생성1");
		System.out.println(str + " 생성2");
		System.out.println(str + " 생성3");
	}
}
class HumanFactory{
	public static Human create(String str) {
		if(str == "남자") {
			return new Man(str);
		}else if(str =="여자") {
			return new Woman(str);
		}
		return null;
	}
}
public class SimpleFactoryPattern {

	public static void main(String[] args) {
		//엄마가 h1을 만들껀데, 남자 클래스에 있는 나의 추상메소드가 부러워서 남자꺼 쓸거야
		Human h1 = HumanFactory.create("남자");  //new Man("남자")
		h1.print();
		//엄마가 h2를 만들껀데, 여자 클래스에 있는 나의 추상메소드가 부러워서 여자꺼 쓸거야
		//단, 부모가 가지고 있던것들만 자식 클래스에 있는걸 사용할 수 있어. 왜냐면 자식이 뭐 만들었는지는 부모가 몰라 문닫고 살아서 몰라
		//자식을 부모에게 대입하여 사용할 수 있다
		Human h2 = HumanFactory.create("여자");  //new Woman("여자")
		h2.print();
	}
}
