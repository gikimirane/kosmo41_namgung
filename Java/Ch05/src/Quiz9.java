/*
���� 05-09  
1���� 100���� �����߿��� ¦���� ���� ���϶�. �� do~while���� �̿��϶�
 */
public class Quiz9 {

	public static void main(String[] args) {
	
		int i = 1;
		int nSum =0;
		
		do {
			if(i % 2 == 0) {
				nSum = nSum + i;
			}
			i++;
		}while(i<101);
		
		System.out.println("1���� 100������ ���� �� ¦���� ���� : "+ nSum);
	}
}