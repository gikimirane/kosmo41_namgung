import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

public class B2_BufferdFileCopier {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("대상 파일 : ");
		String src = sc.nextLine();
		System.out.println("사본 이름 : ");
		String dst = sc.nextLine();
		sc.close();
		   
	
		try(InputStream in = new FileInputStream(src); 
				OutputStream out = new FileOutputStream(dst)){
			byte buf[] = new byte[1024];  //1024바이트까지 읽겠음!
			int len;
			//걸린시간 재려고 각각의 now를 측정하고 사이의 차이를 기재
			LocalTime start = LocalTime.now();
			while(true) {
				len = in.read(buf);  //1KByte씩 불러옴 (읽고)
				if(len == -1) {
					break;	
				}
				out.write(buf,0,len);   //쓰고 반복, 내용이 없어서 -1일 때까지..
			}
			LocalTime end = LocalTime.now();
			System.out.println("걸린 시간 : "+Duration.between(start, end).toMillis());
			System.out.println("복사 완료!");
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
