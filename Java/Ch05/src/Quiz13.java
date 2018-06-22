/*
문제 05-13
중첩 for문을 이용하여 구구단 전체를 출력하는 프로그램을 작성하시오.
단 가로형, 세로형 둘다 만들어야함.

 */
public class Quiz13 {

	public static void main(String[] args) {
		
			//가로
			for(int j=1;j<10;j++) {
				for (int i=2;i<10;i++) {
					System.out.print(i+"*"+j+"="+(i*j)+'\t');
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();
			
			for(int x=2;x<10;x++) {
				for(int y=1;y<10;y++) {
					System.out.print(x+"*"+y+"="+(x*y)+'\t');
				}
				System.out.println();
				System.out.println();
			}
		}
}
