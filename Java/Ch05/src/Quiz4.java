/*
���� 05-01
����ڷκ��� ����ؼ� ������ �Է¹޴´�.
�� 0�� �Է¹ްԵǸ� ������ �Է¹��� ��� ������ ������ ����� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
 */

import java.util.Scanner;
public class Quiz4 {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("0�� �ƴ� ���ڸ� �Է� �� ��� ���ص帳�ϴ�.");
		int num1 = s.nextInt();
		int sum = 0;
		
		do {
			num1 = s.nextInt();
			sum = sum + num1;
			
		}while (num1 != 0);
		
		System.out.println("0�� �Է��Ͽ� ���� ��ȯ�մϴ�. ���� : "+sum);
	
	}
}
