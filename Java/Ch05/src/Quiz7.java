/*
���� 05-07
�л��� ��ü ��������� ���� ������ ����ϴ� ���α׷��� �ۼ��϶�.
����� ����, ����, ������ ������ ���ʷ� �Է¹��� �� ����� ���� ��
90���̻� A, 80���̻� B, 70���̻� C, 50���̻� D, �� �̸��̸� F�� ����Ѵ�.
*/
import java.util.Scanner;
public class Quiz7 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
	
		int nAvg = 0;
		
		System.out.println("���� ������ �Է��ϼ���.");
		int nLan = s.nextInt();
		System.out.println("���� ������ �Է��ϼ���.");
		int nEng = s.nextInt();
		System.out.println("���� ������ �Է��ϼ���.");
		int nMat = s.nextInt();
		
		nAvg = (nLan+nEng+nMat)/3;
		
		if (nAvg >= 90) {
			System.out.println("A");
		}else if (nAvg >= 80) {
			System.out.println("B");
		}else if (nAvg >= 70) {
			System.out.println("C");
		}else if (nAvg >= 50) {
			System.out.println("D");
		}else System.out.println("F");
		
	}

}
