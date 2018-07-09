@FunctionalInterface
interface Calculate3 <T> {
	T cal (T a, T b);	
}
public class B_LambaGeneric {
	public static void main(String[] args) {
		Calculate3<Integer> ci = (a,b) -> a+b;
		System.out.println(ci.cal(4, 3));
		
//		제너릭이라서 어떤 형태가 들어와도 출력이 가능함
		Calculate3<Double> cd = (a,b) -> a+b;
		System.out.println(cd.cal(4.32, 3.45));
		
//		제너릭을 활용하였기 때문에 String도 무관함
		Calculate3<String> cs = (a,b) -> a+b+a;
		System.out.println(cs.cal("남", "궁"));
	}
}
