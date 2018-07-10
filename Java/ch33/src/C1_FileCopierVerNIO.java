import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class C1_FileCopierVerNIO {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("대상 파일 : ");
		Path src = Paths.get(sc.nextLine());
		System.out.println("사본 이름 : ");
		Path dst = Paths.get(sc.nextLine());
		sc.close();

//		하나의 버퍼 생성
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
//		try에서 두개의 채널 생성
		try(FileChannel ifc = FileChannel.open(src, StandardOpenOption.READ);
				FileChannel ofc = FileChannel.open(dst, StandardOpenOption.WRITE,StandardOpenOption.CREATE)){
			int num;
			while(true) {
				num=ifc.read(buf);
				if(num == -1) { //읽어 드린 데이터가 없다면! break~
					break;
				}
				buf.flip(); //모드 변환!
//				put* 함수를 이용해서 값을 쓰거나 SocketChannel 의 read 함수로 ByteBuffer instance 에 값을 쓰고 나서 
//				데이터를 읽기 전에 ByteBuffer 클래스의 flip 함수를 한번 호출해야 한다.
				ofc.write(buf); //버퍼에서 채널 ofc로 데이터 전송
				buf.clear(); //버퍼 비우기, 왜냐면 다시 올라갔을 때 1024만큼 이미 차있기 때문에 버퍼를 비웠음
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
