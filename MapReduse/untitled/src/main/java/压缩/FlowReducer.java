package 压缩;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
    FlowBean outv = new FlowBean();

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Reducer<Text, FlowBean, Text, FlowBean>.Context context) throws IOException, InterruptedException {
        long totaUpFlow = 0;
        long totaDownFlow = 0;
        long totaSunFlow = 0;
        for (FlowBean bean : values
        ) {
            totaUpFlow += bean.getUpFlow();
            totaDownFlow += bean.getDownFlow();
            totaSunFlow += bean.getSumFlow();
        }

        outv.setUpFlow(totaUpFlow);
        outv.setDownFlow(totaDownFlow);
        outv.setSumFlow(totaSunFlow);
        context.write(key,outv);
    }
}
