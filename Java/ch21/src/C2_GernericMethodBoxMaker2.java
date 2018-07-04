class BoxD<T>{
	private T ob;
	public void set (T o) {
		ob = o;
	}
	public T get() {
		return ob;
	}
}
class BoxFactoryD  {
	public static <T> BoxD<T> makeBox(T o){
		BoxD<T> box = new BoxD<T>();
		box.set(o);
		return box;
	}
}
public class C2_GernericMethodBoxMaker2 {

	public static void main(String[] args) {
		BoxD<String> sBox = BoxFactoryD.makeBox("Sweet");
		System.out.println(sBox.get());
		
		BoxD<Double> dBox = BoxFactoryD.makeBox(7.59);
		System.out.println(dBox.get());
	}

}
