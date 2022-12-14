package group;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OrderMapper extends Mapper<LongWritable, Text,OrderBean, NullWritable> {
    private OrderBean outk=new OrderBean();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, OrderBean, NullWritable>.Context context) throws IOException, InterruptedException {
        String line=value.toString();
        String[] split = line.split("\t");
        outk.setOrderId(split[0]);
        outk.setPrice(Double.parseDouble(split[1]));
        context.write(outk,NullWritable.get());
    }
}



