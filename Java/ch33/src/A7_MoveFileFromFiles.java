import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class A7_MoveFileFromFiles {

	public static void main(String[] args) throws IOException {
		Path src = Paths.get("D:\\yh9189\\JavaStudy\\Dir1");
		Path dst = Paths.get("D:\\yh9189\\JavaStudy\\Dir2");
//		기존에 Dir1 폴더가 있어야 함 그래야 Dir2로 이름을 바꿀 수 있다!
		Files.move(src, dst, StandardCopyOption.REPLACE_EXISTING);
//		Dir1 폴더를 Dir2로 바꿔라(내용은 유지, 폴더 이름만 바뀌는 효과가 됨)
	}
}
