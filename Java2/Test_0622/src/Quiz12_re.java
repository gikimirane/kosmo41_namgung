/*
���� 05-12
Do~While���� �̿��Ͽ� 1~1000���� ���ؼ� ����� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
�������� �Ʒ��Ͱ��� ����Ͻÿ�.
��)1+2+3.......+1000 = 500500
 
 */
public class Quiz12_re {

	public static void main(String[] args) {
		
		int i =1;
		int sum = 0;
		
		do {
			
			if(i==1000) {
				sum = sum + i;
				System.out.print(i);
				i++;
			}
			else
			{
				sum = sum + i;
				System.out.print(i+"+");
				i++;
			}	
		}while(i<1001);
		
		System.out.print("="+sum);
	}

}
