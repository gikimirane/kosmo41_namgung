import java.util.Arrays;
import java.util.stream.IntStream;

public class A1_MyFirstStream {

	public static void main(String[] args) {
		int[] ar = {1,2,3,4,5}; 
//		배열 ar로 부터 스트림 생성
		IntStream stm1 = Arrays.stream(ar);
//		중간 연산 진행
		IntStream stm2 = stm1.filter(n -> n%2 ==1);
//		최종연산 진행
		int sum = stm2.sum();
		System.out.println(sum);
	}
}
