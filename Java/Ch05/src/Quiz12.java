/*
문제 05-12
Do~While문을 이용하여 1~1000까지 더해서 결과를 출력하는 프로그램을 작성하시오.
실행결과도 아래와같이 출력하시오.
예)1+2+3.......+1000 = 500500

while 문 작성
		int i=1;
		int sum = 0;
		while (i<1001) {
			if(i==1000) {
				sum = sum+i;
				System.out.println(i);break;
			}else {
				sum = sum+i;
				System.out.print(i+"+");
				i++;
			}
		}
		System.out.print("="+sum);
*/

public class Quiz12 {

	public static void main(String[] args) {
		int i=1;
		int sum = 0;
		
		int num1=0;
		int num2=1;
		/*
		do {
			System.out.println(num2+"+");
			num1=num1+num2;
			num2++;
		}while(num2<=1000);
		System.out.println(" = "+num1);
		*/
		
		while(num2<1001) {
			if(num2 != 1000) {
				System.out.print(num2+"+");
				num1 = num1+num2;
				num2++;
			}
			else {
				num1=num1+num2;
				System.out.println(num2+"="+num1);
				break;
			}
		
		}
		
		
		
		/*do {
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
		*/
	}

}
