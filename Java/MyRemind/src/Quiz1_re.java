import java.util.Scanner;

/*문제 1 : 03-01
사용자로부터 두개의 정수를 입력받아서 사칙연산에 대한 결과를 출력하는 프로그램을 작성하라
*/
public class Quiz1_re {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("사용자 숫자 1 입력");
		int num1 = s.nextInt();
		System.out.println("사용자 숫자 2 입력");
		int num2 = s.nextInt();
		
		MyCalculator calc = new MyCalculator();
		System.out.println("덧셈 : "+calc.Add(num1, num2));
		System.out.println("뺄셈 : "+calc.Sub(num1, num2));
		System.out.println("곱셈 : "+calc.Multi(num1, num2));
		System.out.println("나눗셈 : "+calc.Divi(num1, num2));
		
	}

}
