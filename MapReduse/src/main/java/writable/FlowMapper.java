package writable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable, Text, Text, FlowBean> {
    private Text outk = new Text();
    private FlowBean outv = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, FlowBean>.Context context) throws IOException, InterruptedException {
        String line = value.toString();      //转成String
        String[] flowMsg = line.split("\t");     //分割
        outk.set(flowMsg[0]);              //封装k
        outv.setUpFlow(Long.parseLong(flowMsg[flowMsg.length - 3]));
        outv.setDownFlow(Long.valueOf(flowMsg[flowMsg.length - 2]));
        outv.setSumFlow();                 //封装v
        context.write(outk,outv);          //写出
    }
}
