import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class A3_MakeFileAndDir {

	public static void main(String[] args) throws IOException {
		Path fp = Paths.get("D:\\yh9189\\JavaStudy\\empty.txt");
		fp = Files.createFile(fp);          //파일 생성
		
//		얘는 이미 디렉토리가 있으면 에러내줌
		Path dp1 =Paths.get("D:\\yh9189\\JavaStudy\\Empty");
		dp1 = Files.createDirectory(dp1);   //디렉토리 생성
		
//		얘는 이미 있으면 에러 안내고 실행해주지 않음
		Path dp2 =Paths.get("D:\\yh9189\\JavaStudy\\Empty");
		dp2 = Files.createDirectories(dp2);  // 경로의 모든 디렉토리 생성
		
		
		System.out.println("File : "+fp);
		System.out.println("Dir1 : "+dp1);
		System.out.println("Dir2 : "+dp2);
	}
}