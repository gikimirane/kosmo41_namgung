public class C1_ArrayIndexOfBouns {
	public static void main(String[] args) {
		int[] arr = {1,2,3};
		for(int i=0;i<4;i++) {              
			System.out.println(arr[i]);
		}
	}
}
//arr는 0~2번지까지 총 3개인데 3번지를 출력하려고 하니까 에러가 남
//범위 밖의 배열을 부르려고 하면 없으니까 에러남