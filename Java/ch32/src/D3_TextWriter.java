import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class D3_TextWriter {

	public static void main(String[] args) {
		try(Writer out = new FileWriter("data.txt")){
			for(int ch = (int)'A';ch<(int)('Z'+1);ch++) {
				out.write(ch);
			}
			
			out.write(13); //커서를 현재 행의 다음 행으로, 즉 아래로 내리기
			out.write(10); //커서를 현재 행의 맨 좌측으로 옮기기
			
			//소문자 구하는 방법 (소문자에서 반대로 -32를 하면 대문자가 됨
			for(int ch = (int)'A'+32;ch<(int)('Z'+1+32);ch++) {
				out.write(ch);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
