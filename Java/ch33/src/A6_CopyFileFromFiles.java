import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class A6_CopyFileFromFiles {

	public static void main(String[] args) throws IOException {
		
		Path src = Paths.get("D:\\yh9189\\JavaStudy\\1.java");
		Path dst = Paths.get("D:\\yh9189\\JavaStudy\\2.java");
//		기존에 1.java가 해당 경로에 있어야 함
//		src 경로에 있는 1.java를 dst경로에 2.java로 복사하라! 있어도 복사해!(REPLACE_EXISTING)
		Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
	}
}
