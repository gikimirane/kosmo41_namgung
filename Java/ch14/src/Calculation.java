//추상클래스 = abstract (메소드 중 1개라도 있음 class앞에 써준다)
//추상으로 되어 있으니 니가 바꿔서 써 (구상)
//abstract 부분을 구현하지 않고는 나를 쓸 수 없다! (new 불가)
//나를 상속받아서(날로먹으려면) 쓰려거든 abstract에 있는 메소드를 구현해서(오버라이딩) 써야하느니라~~~
abstract class Calc{
	int a;
	int b;
	abstract void answer();
	
	void setData(int m, int n) {
		a=m;
		b=n;
	}
}

class Plus extends Calc {
	void answer() {    // 상속받은거 오버라이딩 안하면 에러나서 돌릴 수 없음!
		System.out.println(a+" + "+b+" = "+(a+b));
	}
}

public class Calculation {

	public static void main(String[] args) {
		
		Plus plus = new Plus();
		plus.setData(27, 32);
		plus.answer();
	}
}
