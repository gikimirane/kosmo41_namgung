
public class E1_TwoDimenArray_1 {

	public static void main(String[] args) {
		
		int [][] arr = new int [3][4];
		int num=1;
		
		for(int i=0;i<3;i++) {           //조건 j<4를 arr.length 로 변경 가능
			for(int j=0;j<4;j++) {       
				arr[i][j]=num;
				num++;
			}
		}
		for(int i=0;i<3;i++) {
			for(int j=0;j<4;j++) {       //조건 j<4를 arr[i].length로 변경 가능, 아무것도 안쓰면 첫번째, 1개 쓰면 두번째
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}

	}

}
