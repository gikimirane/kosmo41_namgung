import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

//out => 입력하여 내보내기 , in => 읽어오기
public class A6_Read7FromFile3 {
	
	public static void main(String[] args) {
		try(InputStream in = new FileInputStream("data.dat")){
			int dat = in.read();
			System.out.println(dat);
			System.out.printf("%c \n",dat);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
//try 문에 바로 넣으면 out/in.close();하지 않아도 됨!