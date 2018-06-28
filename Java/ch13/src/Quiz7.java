/*
int arr1[2][4] = { {1,2,3,4},{5,6,7,8} };
*/
public class Quiz7 {

	public static void main(String[] args) {
		int ar1[][] = { {1,2,3,4},{5,6,7,8} };
		int[][] ar2 = new int [4][2];
		
		for(int i=0;i<2;i++) {  // i는 0, 1
			for(int j=0;j<4;j++) {  // i=0, j=0,1,2,3 / i=1, j=0,1,2,3
				ar2[j][i] = ar1[i][j];	
			}
		}
		for(int j=0;j<4;j++) {
			for(int i=0;i<2;i++) {
				System.out.print(ar2[j][i]+"\t");
			}
			System.out.println();
		}
	}
}


//배열을 옮길게 아니라 기존 배열을 print 해주면 된다고 함!!! 배열 1개로 끝냈음