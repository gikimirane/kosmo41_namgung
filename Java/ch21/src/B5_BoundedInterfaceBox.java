interface Eatable{
	public String eat();
}
class AppleB implements Eatable{      //인터페이스를 구현했음(추상메소드인 eat메소드)
	public String toString() {
		return "I am an apple.";
	}
	public String eat() {
		return "It tastes so good!";
	}
}
class BoxB<T extends Eatable>{        // 타입을 Eatable에 있는 애나 관련있는애로 제한 했어
	private T ob;
	public void set(T o) {
		ob=o;
	}
	public T get() {
		System.out.println(ob.eat());
		return ob;
	}
}
public class B5_BoundedInterfaceBox {

	public static void main(String[] args) {
		BoxB<AppleB> box = new BoxB<>();     //AppleB가 가능한 이유는 Eatable을 구현했기 때문..
		box.set(new AppleB());
		
		AppleB ap = box.get();
		System.out.println(ap);

	}

}
