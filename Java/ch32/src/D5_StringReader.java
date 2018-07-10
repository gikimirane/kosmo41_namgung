import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class D5_StringReader {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new FileReader("String.txt"))){
			String str;
			while(true) {
				str = br.readLine(); //한줄씩 읽어옴, 더이상 없을 때 얘는 null를 반환함
				if(str == null) break;
				System.out.println(str);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
