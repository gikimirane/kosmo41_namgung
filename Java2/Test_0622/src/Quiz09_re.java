/*
���� 05-09  
1���� 100���� �����߿��� ¦���� ���� ���϶�. �� do~while���� �̿��϶�
 */
public class Quiz09_re {

	public static void main(String[] args) {
		
		int sum =0;
				
		for (int i=1;i<101;i++) {
			if(i%2==0) {
				sum = sum + i;
			}
		}
		System.out.println("���� : "+sum);
		
		/*
		while (i<101) {
			if(i%2==0) {
				sum = sum + i;
			}
			i++;
		}
		System.out.println("�� :"+sum);
		
		
		do {
			if(i % 2 ==0) {
				sum=sum+i;
			}
			i=i+1;
		}while(i<101);
		System.out.println("1���� 100���� ¦���� �� : "+sum);
		 */
	}

}
