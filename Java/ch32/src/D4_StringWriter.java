import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class D4_StringWriter {
//이거를 주로 사용한다고 함! 이게 젤 좋아보임
	public static void main(String[] args) {
		String ks = "공부에 있어서 돈이 꼭 필요한 것은 아니다.";
		String es = "Life is long If you know how to use it.";
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("String.txt"))){
			bw.write(ks,0,ks.length());
			bw.newLine();  //줄바꿈 문자 삽입(운영체제 별로 다르다고 함)
			bw.write(es,0,es.length());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
