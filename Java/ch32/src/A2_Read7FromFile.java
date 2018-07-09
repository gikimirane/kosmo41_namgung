import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class A2_Read7FromFile {

	public static void main(String[] args) {
		
		try {
			InputStream in = new FileInputStream("data.dat");
			int dat = in.read();
			in.close();
			System.out.println(dat);
			System.out.printf("%c",dat);
			
		}catch(IOException e) {
			e.printStackTrace();
//			에러가 나면 내가 처리하겠소
		}
	}
}
