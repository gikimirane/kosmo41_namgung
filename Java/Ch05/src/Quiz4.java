/*
문제 05-04
두개의 정수를 입력 받아서 두 수의 차를 출력하는 프로그램을 작성하라.
단 입력된 두 수의 순서에 상관없이 출력결과는 항상 0 이상이어야 한다.
1과2를 입력했다면 1 .... 20과10을 입력했다면 10 ....
위 프로그램을 if문과 조건연산자를 이용하여 작성해보자.
 */
import java.util.Scanner;
public class Quiz4 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("수 1 입력하세요");
		int nNum1 = s.nextInt();
		System.out.println("수 2 입력하세요.");
		int nNum2 = s.nextInt();
		
		if(nNum1 < nNum2) {
			System.out.println("두 수의 차이는 : " + (nNum2 - nNum1));
		}
		else {
			System.out.println("두 수의 차이는 : " + (nNum1 - nNum2));
		}
	}
}

//삼항 연산자 방법
//int nResult = (nNum1 > nNum2) ? (nNum1-nNum2):(nNum2-nNum1); 


/* 계산 결과가 -일때 강제로 +로 변경하는 방법 (*-1을 하면 됨)
if ((nNum1-nNum2)<0) {
	System.out.println((nNum1-nNum2)*-1);
}else {
	System.out.println(nNum1-nNum2);
}
*/
