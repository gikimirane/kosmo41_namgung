import java.io.*;
import java.util.*;
public class HashMapEx {
//	Map<키, 값> 키는 중복허용하지 않음
//	키가 중복인 경우 값을 변경 함
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("만화", "마녀배달부 키키");
		map.put("호러", "스크림");
		map.put("영화","황혼에서 새벽까지");
		System.out.println(map);
		System.out.println();
		
		String key;
		Set<String> set = map.keySet();
		Iterator it = set.iterator();
		
		while(it.hasNext()) {
			key = (String)it.next();
			System.out.println(map.get(key));
		}System.out.println();
//		황혼에서 새벽까지가 사라지고 영화라는 키의 값은 놀자..로 바뀜
		map.put("영화", "놀자...");  //동일한 키의 경우는 값을 변경한다
		System.out.println(map);
		System.out.println();
	}
}
