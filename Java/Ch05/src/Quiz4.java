/*
���� 05-04
�ΰ��� ������ �Է� �޾Ƽ� �� ���� ���� ����ϴ� ���α׷��� �ۼ��϶�.
�� �Էµ� �� ���� ������ ������� ��°���� �׻� 0 �̻��̾�� �Ѵ�.
1��2�� �Է��ߴٸ� 1 .... 20��10�� �Է��ߴٸ� 10 ....
�� ���α׷��� if���� ���ǿ����ڸ� �̿��Ͽ� �ۼ��غ���.
 */
import java.util.Scanner;
public class Quiz4 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("�� 1 �Է��ϼ���");
		int nNum1 = s.nextInt();
		System.out.println("�� 2 �Է��ϼ���.");
		int nNum2 = s.nextInt();
		
		if(nNum1 < nNum2) {
			System.out.println("�� ���� ���̴� : " + (nNum2 - nNum1));
		}
		else {
			System.out.println("�� ���� ���̴� : " + (nNum1 - nNum2));
		}
	}
}

//���� ������ ���
//int nResult = (nNum1 > nNum2) ? (nNum1-nNum2):(nNum2-nNum1); 


/* ��� ����� -�϶� ������ +�� �����ϴ� ��� (*-1�� �ϸ� ��)
if ((nNum1-nNum2)<0) {
	System.out.println((nNum1-nNum2)*-1);
}else {
	System.out.println(nNum1-nNum2);
}
*/
