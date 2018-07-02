public class Quiz10 {

	public static void main(String[] args) {
		int ar[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		int[][] ar1 = new int[4][4];  	//Print용
		
		//첫번째 배열이 반복될 수 있게 옮기고 print 함				
		System.out.println("초기 배열의 모양");
		for(int i=0,n=3;i<ar.length;i++) {		
			for(int j=0;j<ar.length;j++) {
				
				ar1[j][i+n] = ar[i][j];
				System.out.print(ar1[j][i+n]+"\t");	
			}
			n--;
			System.out.println();
		}
		
		for(int count=1;count<ar.length;count++) {
			System.out.println();
			System.out.println(count+"번째 90도 회전 후 모양");
		
			for(int j=0;j<ar.length;j++) {     //값을 옮기고 찍어냄
				int n=0;
				for(int i=3;i>=0;i--) {
					ar1[j][n] = ar[i][j]; 
					System.out.print(ar1[j][n]+"\t");
					n++;
				}
				System.out.println();
			}
			
			for(int j=0;j<ar.length;j++) {    // 반복을 위해서 다시 ar로 넣어주기
				int n=0;
				for(int i=3;i>=0;i--) {
					ar[j][n] = ar1[j][n];
					n++;
				}
			}
		}
	}
}
