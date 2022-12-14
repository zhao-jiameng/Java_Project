package etl;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class EtlMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
        Text outk = new Text();
        String srcData = value.toString();              //获取一条数据
        String etlData = EtlUtils.etlGulivideo(srcData);//清洗
        if (etlData!=null){                             //写出
            outk.set(etlData);
            context.write(outk,NullWritable.get());
        }
    }
}
