/*
���� 05-01
����ڷκ��� ����ؼ� ������ �Է¹޴´�.
�� 0�� �Է¹ްԵǸ� ������ �Է¹��� ��� ������ ������ ����� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
 */

import java.util.Scanner;
public class Quiz1 {

	public static void main(String[] args) {
	
		Scanner s = new Scanner(System.in);
		
		int sum = 0;
		int num1;
	
		
		System.out.println("���� �Է� (0 ������ ����)");
		
		num1 = s.nextInt();
		
		while (num1 != 0) {
			sum = sum + num1;
			num1 = s.nextInt();
		}
		
		System.out.println("0�� �Է��Ͽ� �հ� ��ȯ : " + sum);
	}
}

/*	
do {
	System.out.println("0�� �ƴ� ���ڸ� �Է� �� ��� ���ص帳�ϴ�.");
	
	num1 = s.nextInt();
	sum = sum + num1;
	
}while (num1 != 0);

System.out.println("0�� �Է��Ͽ� ���� ��ȯ�մϴ�. ���� : "+sum);
*/
