/*
���� 05-03 
1 �̻� 100�̸��� �����߿��� 7�ǹ���� 9�ǹ���� ����ϴ� ���α׷��� �ۼ��϶�.
��, 7�� ����̸鼭 ���ÿ� 9�� ����� ������ �ѹ��� ����ؾ� �Ѵ�.

 */
public class Quiz3 {

	public static void main(String[] args) {
		
		for (int i=1;i<100;i++) {
			if ((i % 7 == 0) || (i % 9 == 0)) {
				System.out.println("7 �Ǵ� 9�� ����� : "+i);
			}
		}
	}
}

/* 7 �Ǵ� 9�� ����� �ֵ��� print �ϰ�, 7�� 9�� ����� 1ȸ�� ��� for, if ���� ����

for(int j=1;j<100;j++) {
	if ((j % 7 == 0) && (j % 9 == 0)) {
		System.out.println("-----------------------");
		System.out.println("7�� 9 ���� ��� : "+j);
		System.out.println("-----------------------");
		break;
	}
}

for(int i=1;i<100;i++) {
	if((i % 7 == 0) || (i % 9 == 0)) {
		
		System.out.println("7 �Ǵ� 9�� ��� : "+i);
		System.out.println("-----------------------");				
	}
}
*/