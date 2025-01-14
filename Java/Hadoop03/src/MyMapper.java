import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper 
				extends Mapper<LongWritable,Text, Text, IntWritable> {

	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		
		//글자 썰고 1 붙혀주는 일만 한다
		StringTokenizer st = new StringTokenizer(value.toString());
		while(st.hasMoreTokens()) {
			word.set(st.nextToken());
			context.write(word, one);
		}
	}
	
}
