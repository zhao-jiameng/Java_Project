package comparable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable, Text,FlowBean,Text> {
        private FlowBean outk=new FlowBean();
        private Text outv=new Text();
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, FlowBean, Text>.Context context) throws IOException, InterruptedException {

                String flowMsg=value.toString();
                String [] split=flowMsg.split("\t");
                outk.setUpFlow(Long.parseLong(split[split.length-3]));
                outk.setDownFlow(Long.parseLong(split[split.length-2]));
                outk.setSumFlow();
                outv.set(split[1]);
                context.write(outk, outv);
        }
}
