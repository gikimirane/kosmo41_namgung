/*
���� 05-12
Do~While���� �̿��Ͽ� 1~1000���� ���ؼ� ����� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
�������� �Ʒ��Ͱ��� ����Ͻÿ�.
��)1+2+3.......+1000 = 500500
 */
public class Quiz12 {

	public static void main(String[] args) {
		int i=1;
		int sum = 0;
		
		do {
			sum = sum +i;		
			if(i==1000) {
				System.out.print(i);
				break;
			}
			else {
				System.out.print(i+"+");
				i++;
			}		
		}while(i<=1000);
		System.out.print("="+sum);
	}

}
