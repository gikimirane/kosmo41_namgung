class Box1<T>{
	protected T ob;
	public void set(T o) {
		ob = o;
	}
	public T get() {
		return ob;
	}
}
class SteelBox1<T> extends Box1<T>{
	public SteelBox1(T o) {   
		ob = o;
	}
}
public class A1_GenericInheritance {

	public static void main(String[] args) {
		Box1<Integer> iBox = new SteelBox1<>(7959); //생성자를 만나서 7959를 넣으면서 생성하겠다는 의미
		Box1<String> sBox = new SteelBox1<>("Simple"); //생성자를 만나서 Simple을 넣으면서 생성
		
		System.out.println(iBox.get());
		System.out.println(sBox.get());
	}
}
//Point! 제너릭도 상속이 된다!