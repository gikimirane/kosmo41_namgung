import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class A4_SimpleBinWriteRead {

	public static void main(String[] args) throws IOException {
		Path fp = Paths.get("D:\\yh9189\\JavaStudy\\simple.bin");
		fp = Files.createFile(fp); //fp에 담긴 path를 토대로 File을 만들고 경로를 넣어라
		byte buf1[] = {0x13,0x14,0x15};  //byte형 buf1배열에 16진수 넣어라(19,20,21)
		for(byte b : buf1) {  //for-each문으로 찍어라
			System.out.print(b+"\t");
		}System.out.println();
		
		//파일에 데이터 쓰기, fp(경로에) buf1에 있는 내용을 넣어라.
		Files.write(fp, buf1, StandardOpenOption.APPEND); //파일의 끝에 데이터를 추가한다는 옵션
		
		//byte형 buf2배열에 fp파일을 다 읽어서 넣어라
		byte buf2[] = Files.readAllBytes(fp);
		System.out.println(fp);
		//buf2를 print해라
		for(byte b : buf2) {
			System.out.print(b+"\t");
		}System.out.println();
	}
}
