import java.util.Arrays;

public class H8_ArraySearch {

	public static void main(String[] args) {
		int[] ar = {33,55,11,44,22};
		Arrays.sort(ar);
		
		for(int n:ar) System.out.print(n+"\t");
		System.out.println();
		
		int idx = Arrays.binarySearch(ar, 33); //배열에서 33을 찾아라!
		System.out.println("Index of 33 : "+idx);
		
		//중요한건 몇번째에 있냐가 아니라 없으면 0보다 작은 값을 반환하기 때문에
		//idx가 0보다 작냐 크냐를 확인해서 있는지 없는지 확인하는것이 더 중요
	}

}
