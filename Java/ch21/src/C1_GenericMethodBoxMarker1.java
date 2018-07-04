class BoxC<T>{
	private T ob;
	public void set(T o) {
		ob=o;
	}
	public T get() {
		return ob;
	}
}
class BoxFactoryC {    //일반 클래스에 메소드만 제너릭임
	public static <T> BoxC<T> makeBox(T o){
		BoxC<T> box = new BoxC<T>();
		box.set(o);
		return box;
	}
}
public class C1_GenericMethodBoxMarker1 {

	public static void main(String[] args) {
		BoxC<String> sBox = BoxFactoryC.makeBox("Sweet");
		System.out.println(sBox.get());
		
		BoxC<Double> dBox = BoxFactoryC.makeBox(7.59);
		System.out.println(dBox.get());

	}

}
