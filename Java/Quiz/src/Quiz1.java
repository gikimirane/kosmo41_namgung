/*
���� 1 : 03-01
����ڷκ��� �ΰ��� ������ �Է¹޾Ƽ� ��Ģ���꿡 ���� ����� ����ϴ� ���α׷��� �ۼ��϶�.
*/

import java.util.Scanner;

public class Quiz1 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int sum=0;
		
		System.out.println("ù ��° ���ڸ� �Է��ϼ���.");
		int num1 = s.nextInt();
		
		System.out.println("�� ��° ���ڸ� �Է��ϼ���.");
		int num2 = s.nextInt();
		
		System.out.println("������ ��� : " + (num1+num2));
		
		// if ������ �غ�
	
		if (num1 > num2 ){
			System.out.println("������ ��� : " + (num1-num2));
		}
		else System.out.println("������ ��� : " + (num2-num1));

		// ���� �����ڷ� Ȱ��
		//sum = num1 > num2 ? (num1-num2) : (num2 - num1);
		System.out.println("������ ��� : " + sum);
		System.out.println("������ ��� : " + (num1*num2));
		System.out.println("�������� ��� : " + (num1/num2));

	}

}
