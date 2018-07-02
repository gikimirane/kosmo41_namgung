/*
문제 3 : 03-03
두개의 정수를 입력받아서 다음과 같은 출력결과를 만들어보자.
출력예) 두개의 정수를 입력하세요 .1
25 4
25 나누기 4 의 몫은 6 입니다.
25 나누기 4 의 나머지는 1 입니다.25

 */
public class Quiz3_re {
	public static void main(String[] args) {
		UserIO in = new UserIO();
		MyCalculator calc = new MyCalculator();
		int num1, num2, n=1;
		num1 = in.UserIn(n++);
		num2 = in.UserIn(n++);
		System.out.println(num1+" / "+num2+"의 몫은 : "+calc.Divi(num1, num2));
		System.out.println(num1+" / "+num2+"의 나머지는 : "+calc.Remain(num1, num2));
	}
}
