/*
문제6
가로가 9, 세로가 3인 int형 2차원 배열을 선언하여 구구단 중 2, 3, 4단을 저장한다.
그리고 제대로 저장이 되었는지 확인하기 위해 출력을 하는 프로그램을 작성하라
 */
public class Quiz6 {

	public static void main(String[] args) {
		
		int[][] ar2 = new int[3][9];
		
		for(int i=0;i<ar2.length;i++) {
			for(int j =0;j<ar2[i].length;j++) {
				int n1=i+2;
				int n2=j+1;
				ar2[i][j] = n1*n2;
				System.out.print(n1+"*"+n2+"="+ar2[i][j]+"\t");
			}	
			System.out.println();
		}	
	}
}
