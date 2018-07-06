public class C1_Varargs {
	//메소드를 오버로딩하지 않고 동일한 자료형이 몇개가 들어올건지..가변함
	//매개변수의 가변인자 선언, 호출
	public static void showAll(String...vargs) {
		System.out.println("LEN : "+vargs.length);
		
		for(String s : vargs) {
			System.out.print(s+"\t");
		}System.out.println();
	}
	public static void main(String[] args) {
		showAll("Box");
		showAll("Box","Toy");
		showAll("Box","Toy","Apple");
	}
}