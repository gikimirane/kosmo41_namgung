import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class D2_IOExceptionCase2 {

	public static void main(String[] args) {
		Path file = Paths.get("D:\\yh9189\\Study\\Simple.txt");
		BufferedWriter writer = null;
		
		try {
			writer = Files.newBufferedWriter(file);
			writer.write('A');
			writer.write('Z');
			if(writer != null) {
				writer.close();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
//에러 날수도 있다고 미리 말해주고 try~catch문을 쓰도록 유도 함
