import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class A5_SimpleTxtWriteRead {
//fp = Files.createFile(fp); <- 안전하게 얘 쓰는게 좋음, 있으면 덮어쓰지 않고 에러를 발생시킴
	public static void main(String[] args) throws IOException {
		Path fp = Paths.get("D:\\yh9189\\JavaStudy\\simple.txt");
//		fp = Files.createFile(fp);  //얘를 쓰니까 2번 추가가 안됨!
		String st1 = "One Simple String";
		String st2 = "Two Simple String";
		
		List<String> lst1 = Arrays.asList(st1,st2);
		Files.write(fp, lst1); 
		
		List<String> lst2 = Files.readAllLines(fp);
		//for-each문으로 1개 문자열씩 출력 가능
		for(String str : lst2) {
			System.out.println(str);
		}
		//객체 그대로 찍어버림(컬렉션이라는 의미로 괄호도 나옴)
		System.out.println(lst2);
	}
}
