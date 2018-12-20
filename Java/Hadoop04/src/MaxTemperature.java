import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MaxTemperature {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		if(args.length !=2 ) {
			System.out.println("Usage : WordCount <input> <output>");
			System.exit(-1);
		}

		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf,"MaxTemperature");
		
		job.setJarByClass(MaxTemperature.class);
		job.setMapperClass(MaxTemperatureMapper.class);
		job.setReducerClass(MyTemperatureReducer.class);
		
		
		//출력키, 값 데이터 형식 지정	
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//입력파일, 출력 디렉터리 지정
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//하둡 분석 작업 진행
		job.waitForCompletion(true);

	}

}
