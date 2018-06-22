/*
문제 05-08
사용자로부터 5개의 정수를 입력받아서 합을 구하여 출력한다.
만약 입력받는 숫자가 1미만의 숫자라면 재입력을 요구해야 한다. 그래서 1이상의 정수 5개를 입력받아야 프로그램이 완성된다.
 */
import java.util.Scanner;
public class Quiz8 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int nNum;
		int nSum = 0;
		for(int i=0;i<5;i++) {
			System.out.println("숫자를 총 5개 입력하세요.");
			nNum = s.nextInt();
			if(nNum > 0) {
				nSum = nSum + nNum;
			}
			else if (nNum <= 0){
				System.out.println("다시");
				i=i-1;
			}
		}
		System.out.println("5개 숫자의 합은 : "+nSum);
	}
}
/*
 * if (nNum <= 0) {
				System.out.println("다시 입력하세요.");
				continue;
			}
			else if (nNum > 0)
			{
				nSum = nSum + nNum;
				i++;
			}
 */
