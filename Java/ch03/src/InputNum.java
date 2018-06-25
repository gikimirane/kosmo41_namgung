import java.util.Scanner;
public class InputNum {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("첫번째 수.");
		int num1 = s.nextInt();
		
		System.out.println("두번째 수.");
		int num2 = s.nextInt();
		
		System.out.println("더한거 "+num1 + ", " + num2);
		
	}

}
