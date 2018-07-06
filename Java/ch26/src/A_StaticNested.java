class Outer{
	private static int num =0;
	static class Nested1 {
		void add(int n) {
			num+=n;
		}
	}
	static class Nested2 {
		int get() {
			return num;
		}
	}
}
public class A_StaticNested {

	public static void main(String[] args) {
		Outer.Nested1 nst1 = new Outer.Nested1();
		nst1.add(5);
		Outer.Nested2 nst2 = new Outer.Nested2();
		
		nst1.add(8);
		System.out.println(nst2.get());	
	}
}
// static 네스티드 클래스는 static 선언이 갖는 특성이 반영된 클래스
// 따라서 자신을 감싸는 외부 클래스의 인스턴스와 상관없이 static 네스티드
// 클래스의 인스턴스 생성 가능
