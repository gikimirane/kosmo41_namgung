class Apple4{
	public String toString() {
		return "I am an apple.";
	}
}
class Orange4{
	public String toString() {
		return "I am an Orange.";
	}
}
class Box4 {
	private Object ob;
	public void set(Object o) {
		ob=o;
	}
	public Object get() {
		return ob;
	}
}

public class A4_FruitAndBoxFault2 {

	public static void main(String[] args) {
		
		Box4 aBox = new Box4();
		Box4 oBox = new Box4();
		
		aBox.set("Apple");
		oBox.set("Orange");
		System.out.println(aBox.get());
		System.out.println(oBox.get());
		
		//에러가 나야하는데 안나는게 문제
		//출력은 되지만 이후 사용시 Apple4, Orange4로 형변환이 필요함, 
		//나중에 형변환 써먹을때 에러가 남!!
	}

}
