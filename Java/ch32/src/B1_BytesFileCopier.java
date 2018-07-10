import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

public class B1_BytesFileCopier {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("대상 파일 : ");
		String src = sc.nextLine();
		System.out.println("사본 이름 : ");
		String dst = sc.nextLine();
		sc.close();
		
		try(InputStream in = new FileInputStream(src); 
				OutputStream out = new FileOutputStream(dst)){
			int data;
			
			LocalTime start = LocalTime.now();
			while(true) {
				data = in.read();  //1byte씩 불러옴 (읽고)
				if(data == -1) {
					break;	
				}
				out.write(data);   //쓰고 반복, 내용이 없어서 -1일 때까지..
			}
			LocalTime end = LocalTime.now();
			System.out.println("걸린 시간 : "+Duration.between(start, end).toMillis());
			
			System.out.println("복사 완료!");
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

}
