import java.util.InputMismatchException;
import java.util.Scanner;

public class A1_ExceptionCase1 {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		
		try {
			System.out.println("a/b....a? ");
			int n1 = kb.nextInt();
			System.out.println("a/b....b?");
			int n2 = kb.nextInt();
			System.out.println(n1+" / "+n2+" = "+n1/n2);
		}
		catch(ArithmeticException e) {      //수학적인 에러
			System.out.println(e.getMessage());
		}catch (InputMismatchException e) { //형이 안맞음(int인데 String이 들어감)
			System.out.println(e.getMessage());
		}
	}
}
