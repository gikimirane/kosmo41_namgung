import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class B3_StringWriterNewVer {

	public static void main(String[] args) {
		String ks = "공부에 있어서...";
		String es = "Life is long if...";
		Path fp = Paths.get("String.txt");
//		절대 경로를 넣지 않아서 현재 경로에 String.txt 파일을 생성했음
		
//		Files라는 클래스의 newbufferedWriter를 메소드를 쓸건데 그 주소값을 bw가 가지고 있음
		try(BufferedWriter bw = Files.newBufferedWriter(fp)){
//			ks문자열에 0부터 길이(끝)까지 write하겠다.
			bw.write(ks,0,ks.length());
			bw.newLine();      //그냥 한줄 추가야 enter
			bw.write(es,0,es.length());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
