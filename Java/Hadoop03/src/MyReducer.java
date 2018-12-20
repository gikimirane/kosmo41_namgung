import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReducer 
				extends Reducer <Text, IntWritable, Text, IntWritable> {
	
	//Shuffling에 의해 끼리끼리 뭉쳐져 있는 애들끼리 돌면서 +1 한다
	private IntWritable result = new IntWritable();
	public void reduce(Text key, Iterable<IntWritable> values, Context context) 
		throws IOException, InterruptedException
	{
		int sum=0;
		for (IntWritable val : values) {
			sum += val.get();
		}		
		result.set(sum);
		context.write(key, result);
	}
	
}
