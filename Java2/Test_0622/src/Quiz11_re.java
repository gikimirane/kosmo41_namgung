/*
���� 05-11
���� �ϳ��� �Է¹����� �� ����
���丮�� �Լ��� ����� ����ϴ� ���α׷��� while���� �̿��Ͽ� �����Ͻÿ�.
��¿�) �Է����� : 5
5*4*3*2*1 = 120

 */

import java.util.Scanner;
public class Quiz11_re {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int sum = 1;
		int num = s.nextInt();
		while (num>0) {
			sum = sum * num;
			num--;
		}
		System.out.println(sum);

	}

}
