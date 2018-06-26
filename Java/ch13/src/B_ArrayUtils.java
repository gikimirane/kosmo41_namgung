import java.util.Arrays;

public class B_ArrayUtils {

	public static void main(String[] args) {
		
		int [] ar1 = new int [10];
		int [] ar2 = new int [10];
		
		Arrays.fill(ar1,7);  //배열 채우기, ar1을 7로 채울꺼야
		System.arraycopy(ar1, 0, ar2, 3, 4); //배열 복사, ar1의 0~ ar2에 3번째에 4개 복사해줘
		
		for(int i =0;i<ar1.length;i++) {
			System.out.print(ar1[i]+"");
			//ar1을 for 문을 통하여 찍어보면서 확인
		}
		System.out.println();
		
		
		for(int i=0;i<ar2.length;i++) {
			System.out.print(ar2[i]+"");
			//ar2을 for 문을 통하여 찍어보면서 확인
		}

	}

}
