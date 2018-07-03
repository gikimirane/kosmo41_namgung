import java.util.Arrays;

public class H5_ArraySort {

	public static void main(String[] args) {
		int[] ar1 = {1,5,3,2,4};
		double[] ar2 = {3.3,2.2,5.5,1.1,4.4};
		
		//정렬해주는 메소드
		Arrays.sort(ar1);
		Arrays.sort(ar2);
		
		//ar1을 차례로 n에 넣겠다
		for(int n:ar1) System.out.print(n+"\t");
		System.out.println();
		
		for(double d:ar2) System.out.print(d+"\t");
		System.out.println();

	}

}
