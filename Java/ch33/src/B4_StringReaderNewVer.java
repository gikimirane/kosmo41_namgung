import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class B4_StringReaderNewVer {

	public static void main(String[] args) {
		Path fp = Paths.get("String.txt");
		
//		fp에 있는 애를 읽는 메소드의 주소를 br이 가리키고 있음
		try(BufferedReader br = Files.newBufferedReader(fp)){
			String str;
			while(true) {
//				br(=String.txt)를 한줄씩 읽어서 str에 넣어서 출력해!
				str = br.readLine();
				if(str == null) break; //대신 마지막라인이면 멈춰
				System.out.println(str);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
