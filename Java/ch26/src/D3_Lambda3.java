interface PrintableC{
	void print(String s);
}
public class D3_Lambda3 {

	public static void main(String[] args) {
		
		PrintableC prn = (s) -> {System.out.println(s);};
		prn.print("What is Lambda?");
	}
}
//익명 클래스를 보다 쉽게 사용할 수 있도록
//(인자) -> 실행할 구문