import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsExam {

	public static void main(String[] args) {
		
		//파라미터가 2개가 아니면 종료, 파일 이름이랑 내용 받아야 하니까
		if(args.length !=2 ) {
			System.out.println("Usage : HdfsExam <fileName> <contents>");
			System.exit(-1);
		}
		
		try {
			
			Configuration conf = new Configuration();
			FileSystem hdfs = FileSystem.get(conf);
			Path path = new Path(args[0]);
			
			//하둡 파일시스템에 이미 파일이 있으면 삭제
			if(hdfs.exists(path)) {
				hdfs.delete(path,true);
			}
			
			//하둡 파일 시스템에 파일을 생성하고 내용을 저장
			FSDataOutputStream os = hdfs.create(path);
			os.writeUTF(args[1]);
			os.close();
			
			//하둡 파일 시스템의 파일에서 내용을 읽기
			FSDataInputStream is = hdfs.open(path);
			String inputString = is.readUTF();
			is.close();
			System.out.println("Input text : "+inputString);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
