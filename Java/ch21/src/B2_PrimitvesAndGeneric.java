class Box<T>{
	private T ob;
	
	public void set(T o) {
		ob = o;
	}
	public T get() {
		return ob;
	}
}
public class B2_PrimitvesAndGeneric {

	public static void main(String[] args) {
		Box<Integer> iBox = new Box<Integer>();
		iBox.set(125);           //오토박싱
		int num = iBox.get();    //오토 언박싱
		System.out.println(num);
	}
}
//Box<int> box = new Box<int>(); 이런식으로 기본자료형이 올 수 없음!!!
//그래서 Wrapper class를 이용하여 Integer를 부름!!!!!
//Box<Integer> iBox = new Box<>(); 이것도 가능 , 다이아몬드 기호라고 부름