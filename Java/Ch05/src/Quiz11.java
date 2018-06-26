/*
정수 하나를 입력받은후 그 수의
팩토리얼 함수의 결과를 출력하는 프로그램을 while문을 이용하여 구현하시오.
출력예) 입력정수 : 5
5*4*3*2*1 = 120

*/
import java.util.Scanner;
public class Quiz11 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("입력 ㄱㄱ.");
		int nNum1 = s.nextInt();
		int nNum2 = 1;
		
		while(nNum1 > 0) {
			nNum2 = nNum2 * nNum1;
			nNum1 = nNum1-1;
		}
		System.out.println("결과 : "+nNum2);
	}
}
