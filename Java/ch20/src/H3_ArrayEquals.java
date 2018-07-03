import java.util.Arrays;

public class H3_ArrayEquals {

	public static void main(String[] args) {
		int[] ar1 = {1,2,3,4,5};
		int[] ar2 = Arrays.copyOf(ar1, ar1.length);
		//ar1에 있는걸 ar2에 복사해, ar1부터~ 그 길이만큼(모두해라)
		System.out.println(Arrays.equals(ar1, ar2));
		//ar1 배열과 ar2 배열이 같아?

	}

}
