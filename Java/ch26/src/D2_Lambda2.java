interface PrintableB{
	void print(String s);
}
public class D2_Lambda2 {

	public static void main(String[] args) {
		PrintableB prn = new PrintableB() {
			public void print(String s) {
				System.out.println(s);
			}
		};
		prn.print("What is Lambda?");
	}
}
//interface의 구현을 객체 생성할 때 했음, 익명클래스