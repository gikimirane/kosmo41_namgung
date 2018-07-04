class Apple5 {
	public String toString() {
		return "I am an apple.";
	}
}
class Orange5 {
	public String toString() {
		return "I am an Orange.";
	}
}
class WaterMelon {
	public String toString() {
		return "나는야 WaterMelon";
	}
}
class Box5<T> {    //나를 만들때 아무 타입이나 들어와 다 바꿔줄게
	private T ob;  //주로 Type의 T를 사용함
	public void set(T o) {
		ob = o;
	}
	public T get() {
		return ob;
	}
}

public class A5_FruitAndBox2_Generic {

	public static void main(String[] args) {
		Box5<Apple5> aBox = new Box5<Apple5>();
		Box5<Orange5> oBox = new Box5<Orange5>();
		Box5<WaterMelon> wBox = new Box5<WaterMelon>();
	
		aBox.set(new Apple5());
		oBox.set(new Orange5());
		wBox.set(new WaterMelon());
		
		//과일을 박스에 담은 것일까?
		//aBox.set("Apple");  에러가 남 이유는 new 할때 Box5 타입을 Apple5로 만들었기 때문
		//oBox.set("Orange"); String을 넣고 싶으면 String으로 별도로 만들어야 함
		
		Apple5 ap = aBox.get();
		Orange5 or = oBox.get();
		WaterMelon wm = wBox.get();
		
		System.out.println(ap);
		System.out.println(or);
		System.out.println(wm);
		
		

	}

}
