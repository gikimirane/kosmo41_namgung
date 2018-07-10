import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class C2_FileRandomAccess {

	public static void main(String[] args) {
		Path fp = Paths.get("data.dat");
		//put 함수를 이용해서 쓰기
		ByteBuffer wb = ByteBuffer.allocate(1024);
		wb.putInt(120);
		wb.putInt(240);
		wb.putDouble(0.94);
		wb.putDouble(0.75);
		
//		하나의 채널 생성
		try(FileChannel fc = FileChannel.open(fp, StandardOpenOption.CREATE,
				StandardOpenOption.READ,StandardOpenOption.WRITE)){
			
//			wb에 값을 쓰거나 읽기위한 준비.
			wb.flip(); //방금전까지 write하던애라서 flip을 하면 0으로 맨앞으로 이동함
			fc.write(wb);
			
//			파일로부터 읽기
			ByteBuffer rb = ByteBuffer.allocate(1024);
			fc.position(0);   //맨 위로 포지선 이동함!
			fc.read(rb); //0~1024만큼 읽었어, 모든 데이터를 다 읽어옴! 읽을 때 버퍼를 사용하겠다고 표시!
			
//			이하 버퍼로부터 데이터 읽기
			rb.flip(); //버퍼의 맨처음으로 가는거래
			System.out.println(rb.getInt());   //크기만큼 자동으로 이동하면서 읽음
			rb.position(Integer.BYTES*2);      //포지션 8에 강제로 가져다 뒀음! int데이터 2개를 건너뛰고 싶어서
			System.out.println(rb.getDouble());
			System.out.println(rb.getDouble());
			
			rb.position(Integer.BYTES);        //두번째 int를 읽기위해 4로 강제로 이동했음
			System.out.println(rb.getInt());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
// Integer.BYTES : int 형 정수의 크기에 대한 상수 (=4)