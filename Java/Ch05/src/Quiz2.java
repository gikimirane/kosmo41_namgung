/*
���� 05A-02
����ڷκ��� ���� ������� ������ �Է¹��� �� ����� ���Ͽ� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
��� ������ �Է��� �� ����ڷκ��� �Է¹޴´�.
�Է¹��� ���ڸ�ŭ ������ �Է¹޴´�.(1���� 3�̶�� �Է��ߴٸ� 3���� ������ �Է¹޾ƾ� �Ѵ�)
�Է¹��� ���ڵ��� ��հ��� ���Ͽ� ����Ѵ�. ��հ��� �Ҽ��� ���ϱ��� ����ؾ� �Ѵ�.
*/

import java.util.Scanner;
public class Quiz2 {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		//�̰� for ������ ®��
		int sum= 0; // �հ� ����
		int jum = 0; //���� ����
		double avg;  //����� ���� ����
		
		System.out.println("��� ������ �����ðڽ��ϱ�?");
		int count = s.nextInt();
		
		for (int i=1;i<=count;i++) {
			System.out.println(i+"��° ������ ��������.");
			jum = s.nextInt();
			sum = sum + jum;
		}
		avg = sum/count;
		System.out.println("�Է��� ������ ����� : "+avg);
	}

}

/*do while ������ ®��
int nsum=0;
int i=1;
double navg;

System.out.println("��� �Է¹�������");
int count = s.nextInt();

do {
	
	System.out.println("�������� �Է���");
	int njum = s.nextInt();
	nsum = nsum + njum;
	i++;
	
}while(i<=count);

navg = nsum / count;
System.out.println("����� ���ְڴ� : "+ navg);

*/

/* �̰� While ������ ®��
int i=1;
int nsum=0;
double davg;

System.out.println("��� �Է¹�������");
int count = s.nextInt();

while (i<=count) {
	System.out.println("�����̾�, Ƚ����ŭ ����");
	int njum = s.nextInt();
	nsum = nsum + njum;
	i++;
}

davg = nsum / count;
System.out.println("�Է��Ѱ� ��ճ��ٰ� : "+davg);
*/


/* �����ϴ� ��� ����� �˾ƾ� ��!

3�� ���� �ݺ��Ѵٰ� ������ �ϰ� ������ ���� �Ẹ��
3���� ���ư��Բ��� �ڵ��ϰ�
�� ������ ������ �ְ� ���� �����ϰ� �����ϱ�

ó������ ���ø��� ����!
���� �տ� ������ ���������� ���ڸ��� ������ ����, ȸ�縶�� ���ֿ̹� ���� Rule�� ����
*/