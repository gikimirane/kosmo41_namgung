/*
���� 05-11
���� �ϳ��� �Է¹����� �� ����
���丮�� �Լ��� ����� ����ϴ� ���α׷��� while���� �̿��Ͽ� �����Ͻÿ�.
��¿�) �Է����� : 5
5*4*3*2*1 = 120
*/
import java.util.Scanner;
public class Quiz11 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("���丮�� �� �� �Է��ϼ���.");
		int nNum1 = s.nextInt();
		int nNum2 = 1;
		
		while(nNum1 > 0) {
			nNum2 = nNum2 * nNum1;
			nNum1 = nNum1-1;
		}
		System.out.println("�Է� ������ ���� ���丮�� ��� : "+nNum2);
	}
}
