/*
문제 05-10
사용자로부터 2개의 정수를 입력받는다. 가령 2와 6을 입력받았다면
2+3+4+5+6을 구하는 프로그램을 작성하라.
단 6과 2를 입력받는다면 6+5+4+3+2를 구해야 한다. 

 */
import java.util.Scanner;
public class Quiz10 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("값 1");
		int nNum1 = s.nextInt();
		System.out.println("값 2");		
		int nNum2 = s.nextInt();
		int nSum = 0;
		
			if(nNum1 > nNum2) {
				for (int i=nNum1;i>=nNum2;) {
				nSum = nSum + i;
				i--;
				}
			}
			else 
			{
				for (int i=nNum1;i<=nNum2;) {
					nSum = nSum + i;
					i++;
			}
		}
		System.out.println("결과 : "+nSum);	
	}
}
