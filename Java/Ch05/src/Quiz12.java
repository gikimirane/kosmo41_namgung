/*
문제 05-12
Do~While문을 이용하여 1~1000까지 더해서 결과를 출력하는 프로그램을 작성하시오.
실행결과도 아래와같이 출력하시오.
예)1+2+3.......+1000 = 500500
 */
public class Quiz12 {

	public static void main(String[] args) {
		int i=1;
		int sum = 0;
		
		do {
			sum = sum +i;		
			if(i==1000) {
				System.out.print(i);
				break;
			}
			else {
				System.out.print(i+"+");
				i++;
			}		
		}while(i<=1000);
		System.out.print("="+sum);
	}

}
