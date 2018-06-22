/* 
문제 05-05
구구단을 출력하되 짝수단만 출력하도록 프로그램을 작성하라.
단, 2단은 2*2까지만, 4단은 4*4까지만....8단은 8*8까지만 출력해야 한다.
 */
public class Quiz5 {
	public static void main(String[] args) {
		
		for(int i=2;i<10;i++) {
			for(int j=1;j<=i;j++) {
				if((i % 2==0)) {
					System.out.println(i+" * "+j+" = "+(i*j));
				}
			}
			System.out.println();
		}
	}
}
/* for 문에서 아예 짝수만 넣을수도 있음
		for (int i=2;i<10;i=i+2) {
			for (int j=1;j<=i;j++) {
				System.out.println(i+" * "+j+" = "+(i*j));
			}
			System.out.println();
		}
*/