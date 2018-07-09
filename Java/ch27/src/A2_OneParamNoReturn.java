interface Printable { 
	void print(String s);
}
//class imple implements Printable {
//	public void print(String s) {
//		System.out.println(s);
//	}
//}
public class A2_OneParamNoReturn {

	public static void main(String[] args) {
		Printable p;
		//메소드를 한줄로 정의해버림
//		원래대로 한다면...
		
//		imple im = new imple();
//		im.print("Lambda 안쓴거");

		p = (String s) -> { System.out.println(s); };
		p.print("Lambda exp one.");
		
		p = (String s) -> System.out.println(s);
		p.print("Lambda exp two.");
		
		p = (s) -> System.out.println(s);
		p.print("Lambda exp three.");
		
		p = s -> System.out.println(s);
		p.print("Lambda exp four.");
	}
}
// 메서드 몸체가 둘 이상의 문장으로 이뤄져 있거나,
// 매개변수의 수가 둘 이상인 경우에는
// 각각 중괄호와 소괄호의 생략이 가능하다