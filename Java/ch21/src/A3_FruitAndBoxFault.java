class Apple3 { 
	public String toString() {
		return "I am an apple.";
	}
}
class Orange3 { 
	public String toString() {
		return "I am an Orange.";
	}
}
class Box3 { 
	private Object ob;
	public void set(Object o) {
		ob = o;
	}
	public Object get() {
		return ob;
	}
}
public class A3_FruitAndBoxFault {

	public static void main(String[] args) {
		//프로그래머의 실수가 실행과정에서 발견되지 않음! 정말 큰 문제!!!!
		Box3 aBox = new Box3();
		Box3 oBox = new Box3();
		
		aBox.set("Apple");   // String 형 apple도 오브젝트라 여기까진 에러가 안남
		oBox.set("Orange");  // String 형 apple도 오브젝트라 여기까진 에러가 안남
		
		//넣은게 없는데 get하면 안됨
		Apple3 ap = (Apple3)aBox.get();
		Orange3 or = (Orange3)oBox.get();
		
		System.out.println(ap);
		System.out.println(or);
	}
}
