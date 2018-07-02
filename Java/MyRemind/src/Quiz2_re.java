/*
문제 2 : 03-02
하나의 정수를 입력받아서 그 수의 제곱을 출력하는 프로그램을 작성하라. 
가령 5를 입력받았다면 25가 출력되어야 한다.
*/
public class Quiz2_re {

	public static void main(String[] args) {
		int num1;
		UserIO in = new UserIO();
		MyCalculator calc = new MyCalculator();
				
		num1 = in.UserIn(1);
		System.out.println("제곱 : "+calc.Squ(num1));

	}

}
