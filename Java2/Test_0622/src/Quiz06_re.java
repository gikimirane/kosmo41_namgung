/*
문제 05-06
사용자로부터 정수 하나를 입력받은 후 그 수에 해당하는 구구단을 역순으로 출력하시오.	
*/
import java.util.Scanner;	
public class Quiz06_re {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		//System.out.println("원하는 단 입력");
		//int nDan = s.nextInt();
		
		//while 로 구구단 짜보기
		
		int j=9, count=0;
		int i=s.nextInt();
		
		while(j>0) {
			System.out.println(i+"*"+j+"="+i*j);
			j--;
		}
			
		/*
		for(int j=1;j<10;j++) {
			System.out.println(nDan+"*"+j+"="+nDan*j);
		}
		*/

	}

}
