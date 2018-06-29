abstract class Calc2 {     //부모 클래스
	int a;
	int b;
	
	abstract int result(); //추상 메소드
	
	void printResult() {
		System.out.println(result());
	}
	
	void setData(int m, int n) {
		a =m;
		b =n;
	}
}

class Plus extends Calc2 {   //Calc2를 상속받았어
	int result() {           //추상메소드라 구현했어
		return a+b;
	}
}

class Minus extends Calc2 {  //Calc2를 상속받았어
	int result() {           //추상메소드라 구현했어
		return a-b;
	}
}
public class Calculation2 {

	public static void main(String[] args) {
		int x=54;
		int y=12;
		
		//클래스명 변수명;   클래스 객체를 참조하기 위한 참조변수 선언
		//변수명 = new 클래스명;    클래스 객체를 생성 후 객체의 주소를 참조변수에 저장
		Calc2 calc1 = new Plus();    //Calc2
		Calc2 calc2 = new Minus();
		
		calc1.setData(x, y);
		calc2.setData(x, y);
		
		System.out.print(x+"+"+y+"=");
		calc1.printResult();
		System.out.print(x+"-"+y+"=");
		calc2.printResult();
	}

}
