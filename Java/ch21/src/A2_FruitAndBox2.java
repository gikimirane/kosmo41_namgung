class Apple2 {
	public String toString() {       //기본적으로 상속받는 object 클래스의 메소드를 오버라이딩 한거야
		return "I am an apple.";     //Apple2 만 돌려도 I am an apple가 나와
	}
}
class Orange2{
	public String toString() {
		return "I am an Orange.";
	}
}
class Box2 { 
	private Object ob;
	
	public void set(Object o) {
		ob = o;
	}
	public Object get() {
		return ob;
	}
}
public class A2_FruitAndBox2 {

	public static void main(String[] args) {
		Box2 aBox = new Box2();
		Box2 oBox = new Box2();
		
		//과일을 박스에 담는다
		aBox.set(new Apple2());       // Apple2() = "I am an apple"가 됨
		oBox.set(new Orange2());
		
		//박스에서 과일을 꺼낸다
		Apple2 ap = (Apple2)aBox.get();   
		Orange2 og = (Orange2) oBox.get();
		
		System.out.println(ap);
		System.out.println(og);

	}

}
