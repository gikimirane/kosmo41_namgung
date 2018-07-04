class BoxA <T extends Number>{
	private T ob;
	public void set (T o) {
		ob = o;
	}
	public T get() {
		return ob;
	}
}
public class B4_BounedBox {

	public static void main(String[] args) {
		BoxA<Integer> iBox = new BoxA<>();
		iBox.set(24);
		
		BoxA<Double> dBox = new BoxA<>();
		dBox.set(5.97);
		
		System.out.println(iBox.get());
		System.out.println(dBox.get());
	}
}
//T는 Number를 상속받았기 때문에 Number가 아닌 애들은 올 수 없다!
//예를 들어 BoxA<String> sBox = new BoxA<>(); 이런거 안 됨. String은 Number소속이 아니라서..
