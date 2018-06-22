/*
문제 05-09  
1부터 100까지 정수중에서 짝수의 합을 구하라. 단 do~while문을 이용하라
 */
public class Quiz09_re {

	public static void main(String[] args) {
		
		int sum =0;
				
		for (int i=1;i<101;i++) {
			if(i%2==0) {
				sum = sum + i;
			}
		}
		System.out.println("합은 : "+sum);
		
		/*
		while (i<101) {
			if(i%2==0) {
				sum = sum + i;
			}
			i++;
		}
		System.out.println("합 :"+sum);
		
		
		do {
			if(i % 2 ==0) {
				sum=sum+i;
			}
			i=i+1;
		}while(i<101);
		System.out.println("1부터 100까지 짝수의 합 : "+sum);
		 */
	}

}
