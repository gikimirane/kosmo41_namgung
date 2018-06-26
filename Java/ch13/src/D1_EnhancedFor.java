public class D1_EnhancedFor {

	public static void main(String[] args) {
		int[] ar = {1,2,3,4,5};
		
		//배열의 요소 전체출력
		for(int e:ar) {      // e 를 이용하여 ar의 배열의 주소값(0~n번째)까지 1개씩 순차적으로..
			System.out.print(e+" ");
		}
		System.out.println();
		//배열 요소의 합 전체출력
		int sum=0;
		for (int e:ar) {
			sum=sum+e;
		}
		System.out.println("sum : "+sum);
	}
}