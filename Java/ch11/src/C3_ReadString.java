import java.util.Scanner;

public class C3_ReadString {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("문자열 입력");
		String str1 = sc.nextLine();
		
		System.out.println("문자열 입력");
		String str2 = sc.nextLine();
		
		System.out.printf("입력된 문자열 1 : %s \n",str1);
		System.out.printf("입력된 문자열 2 : %s \n",str2);
		
		//sc.close();  < 더이상 스캐너를 사용하지 않겠다는 의미	
	}
}

//int     nextInt()
//byte    nextByte()
//String  nextLine()
//double  nextDouble()
//boolean nextBoolean()
//위와 같이 받을 수 있음