interface Printable4 {
	void print(String doc);
	default void printCMYK(String doc) {}
	//default를 붙히면 추상클래스가 아니고 비어있는 완전한 메소드가 됨
	//오버라이딩 할사람은 하고 안할사람은 하지마슈st
}

class Prn731Drv4 implements Printable4{
	public void print(String doc) {
		System.out.println("From MD-909 Black & White ver");
		System.out.println(doc);
	}
	//얘는 printCMYK 구현하지 않음, 그러나 에러나지 않았음
	//이유는 이미 온전한 메소드라서 구현하지 않아도 에러는 나지 않음, 다만 필요 시 고쳐쓸 수는 있음
}

class Prn909Drv4 implements Printable4 {
	public void print(String doc) {
		System.out.println("From MD-909 Black & White ver");
		System.out.println(doc);
	}
	@Override
	public void printCMYK (String doc) {
		System.out.println("From MD-909 CMYK ver");
		System.out.println(doc);
	} //속 안이 비어있는 메소드를 오버라이딩 하여 고쳐씀!
}
public class B4_PrintDriver4 {
	public static void main(String[] args) {
		String myDoc = "This is report about...";
		Printable4 prn = new Prn909Drv4(); //Prn909Dv4의 인스턴스를 Printable4의 prn으로 쓰겠다!
		prn.print(myDoc);
		System.out.println();
		prn.printCMYK(myDoc);
	}
}
