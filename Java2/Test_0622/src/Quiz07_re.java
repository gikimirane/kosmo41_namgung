/*
���� 05-07
�л��� ���� ��ü ��������� ���� ������ ����ϴ� ���α׷��� �ۼ��϶�.
����� ����, ����, ������ ������ ���ʷ� �Է¹��� �� ����� ���� ��
90���̻� A, 80���̻� B, 70���̻� C, 50���̻� D, �� �̸��̸� F�� ����Ѵ�.

 */

import java.util.Scanner;
public class Quiz07_re {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int nJum1 = s.nextInt();
		int nJum2 = s.nextInt();
		int nJum3 = s.nextInt();
		
		int nAvg = (nJum1+nJum2+nJum3)/3;
			
		switch(nAvg/10) {
		case 9: System.out.println("A");
		case 8: System.out.println("B");
		case 7: System.out.println("C");
		case 6: System.out.println("D");
		default : System.out.println("F");
		}

	}

}
