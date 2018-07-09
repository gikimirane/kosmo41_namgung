import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class A1_Write7ToFile {

	public static void main(String[] args) throws IOException {
		OutputStream out = null;
		try {
			out = new FileOutputStream("data.dat");
			out.write(65);
		}
		finally {
			if(out!=null) {
				out.close();
			}
		}
	}
}
