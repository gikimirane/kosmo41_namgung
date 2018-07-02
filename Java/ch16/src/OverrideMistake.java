class ParentAdder {
	public int add(int a, int b) {
		System.out.println("부모 add");
		return a+b;
	}
}
class ChildAdder extends ParentAdder {
	//@Override (보조문법)
	//이 아니고 Overload 한것임(인자가 다르고 메소드 명이 같은..)
	public double add(double a, double b) {
		System.out.println("덧셈을 진행합니다.");
		return a+b;
	}
}
public class OverrideMistake {
	public static void main(String[] args) {
		ParentAdder adder = new ChildAdder();
		System.out.println(adder.add(3, 4));
		//double 형을 부르고 싶었으나 매개변수가 int 형이라 부모 클래스의 메소드가 호출되어 버림.
	}

}

//시그너처가 같으면 오버라이딩
//시그너처가 다르고 메소드 명 같으면 오버로딩
//@Override 상위 클래스의 메서드를 오버라이딩 하는것이 목적이라는 선언!
//오버라이딩하는 형태가 아니라면 컴파일러가 오류 메시지를 전달 함!