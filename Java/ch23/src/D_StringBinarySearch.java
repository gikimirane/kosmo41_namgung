import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class D_StringBinarySearch {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("Box");
		list.add("Robot");
		list.add("Apple");
		//정렬
		Collections.sort(list);
		//탐색 (검색해서 위치값을 idx에 저장)
		int idx = Collections.binarySearch(list, "Robot");
		//바로 idx를 찍으면 2로 나와버림, get을 이용하여 2번째를 가져옴
		System.out.println(list.get(idx));
	}
}
