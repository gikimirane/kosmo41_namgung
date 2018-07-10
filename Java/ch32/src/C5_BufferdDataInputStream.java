import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class C5_BufferdDataInputStream {
// 데이터를 여러번 감싸서 읽고/쓰고 함
// 파일에 기본 자료형 데이터를 저장하고 싶은데 버퍼링 기능도 추가했으면 좋겠다!
	public static void main(String[] args) {
		try(DataInputStream in =
				new DataInputStream(
						new BufferedInputStream(
								new FileInputStream("data.dat")))){
			int num1 = in.readInt();
			double num2 = in.readDouble();
			
			System.out.println(num1);
			System.out.println(num2);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
