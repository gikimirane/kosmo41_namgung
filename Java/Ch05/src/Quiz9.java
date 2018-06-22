/*
문제 05-09  
1부터 100까지 정수중에서 짝수의 합을 구하라. 단 do~while문을 이용하라
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
		
		System.out.println("1부터 100까지의 정수 중 짝수의 합은 : "+ nSum);
	}
}