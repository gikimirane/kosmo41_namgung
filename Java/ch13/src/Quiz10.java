public class Quiz10 {

	public static void main(String[] args) {
		int[][] ar = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		int[][] ar1 = new int[4][4]; //값 복사용

		int count=1;
		//현재 ar1 초기화된 값을 찍어줌
		for(int i=0;i<ar.length;i++) {
			for(int j=0;j<ar.length;j++) {
				System.out.print(ar[i][j]+"\t");
			}
			System.out.println();
		}
		//4바퀴를 돌자! 처음부터 다시 처음으로
		while(count<ar.length+1) {
			
			//규칙을 적어보니 (3,0) -> (0,0) / (2,0) -> (0,1) / (1,0) -> (0,2) / (0,0) -> (3,0)으로 이동되어 배열 복사 중..
			for(int i=0;i<ar.length;i++) {
				int n=3;
				for(int j=0;j<ar.length;j++) {
					ar1[i][j] = ar[n][i];
					n--;
				}
			}
			System.out.println("-------------- "+count+"회전 --------------");
			//복사된 배열을 순서대로 0,0 부터~ 찍어주기
			for(int i=0;i<ar.length;i++) {
				for(int j=0;j<ar.length;j++) {
					System.out.print(ar1[i][j]+"\t");
					ar[i][j] = ar1[i][j];  //현재 찍어주는 숫자들을 다시 0,0에 대입하여 위의 for문을 재사용 할 수 있도록 함
				}
				System.out.println();
			}
			count++;
		}
	}
}
