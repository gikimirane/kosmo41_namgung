//같은 Class에 동일한 메소드 명이지만, 다른 파라메타, 결과를 가진 메서드를 여러개 선언하는 것 = 오버로딩
//같은 프로젝트 내에는 동일한 이름의 Class는 있으면 안된다.
class Calc3{  
	int add(int a,int b) {
		return a+b;
	}
	int add (int a) {
		return a+1;
	}
	double add (double a, double b) {
		return a+b;
	}
}

class Calculation2 {

	public static void main(String[] args) {
		Calc3 calc3 = new Calc3();
		int nRtn1 = calc3.add(3, 9);
		int nRtn2 = calc3.add(3);
		double nRtn3 = calc3.add(3.0, 9.0);
		
		System.out.println("Rtn1 = "+nRtn1);
		System.out.println("Rtn2 = "+nRtn2);
		System.out.println("Rtn3 = "+nRtn3);
	}
}
