import java.util.Scanner;

public class C1_ScanningString {

	public static void main(String[] args) {
		String source ="1 3 5";
		Scanner sc = new Scanner(source); 
		//source란 System.in (<키보드)와 같은거
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int num3 = sc.nextInt();
		
		int sum = num1+num2+num3;
		System.out.printf("%d + %d + %d = %d \n",num1,num2,num3,sum);

	}

}
