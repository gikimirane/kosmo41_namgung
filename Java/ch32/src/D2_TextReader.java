import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class D2_TextReader {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("읽을 파일 : ");
		String src = sc.nextLine();
		sc.close();
		
		try(Reader in = new FileReader(src)){
			int ch;
			
			while(true) {
				ch = in.read();
				if(ch == -1) break;
				System.out.print((char)ch);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
