import java.util.Scanner;

/*
���� 3 : 03-03
�ΰ��� ������ �Է¹޾Ƽ� ������ ���� ��°���� ������.
��¿�) �ΰ��� ������ �Է��ϼ���.
25 4
25 ������ 4 �� ���� 6 �Դϴ�.
25 ������ 4 �� �������� 1 �Դϴ�.

 */
public class Quiz3 {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		
		System.out.println("ù ��° ���ڸ� �Է��ϼ���.");
		int num1 = s.nextInt();
		
		System.out.println("�� ��° ���ڸ� �Է��ϼ���.");
		int num2 = s.nextInt();
		
		System.out.println(num1 + " ������ " + num2 + "�� ���� "+ (num1/num2)+ " �Դϴ�.");
		System.out.println(num1 + " ������ " + num2 + "�� �������� "+ (num1%num2)+ " �Դϴ�.");

	}

}
