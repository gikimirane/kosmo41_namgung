/*
사용자로부터 5개의 정수를 입력받아서 합을 구하여 출력한다.
만약 입력받는 숫자가 1미만의 숫자라면 재입력을 요구해야 한다. 그래서 1이상의 정수 5개를 입력받아야 프로그램이 완성된다.
 */
import java.util.Scanner;
public class Quiz08_re {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int sum=0,num;
		for (int i=0;i<5;i++) {
			num = s.nextInt();
			if(num <0) {
				i=i-1;
				System.out.println("다시");
			}
			sum = sum+num;
		}
		System.out.println("끝남 합은 "+sum);

	}

}
