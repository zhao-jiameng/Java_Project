package outputformat;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 继承FileOutputFormat
 */
public class LogOutputFormat extends FileOutputFormat<Text, NullWritable> {
    @Override       //写出数据方法
    public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        LogRecordWriter lofrw=new LogRecordWriter(taskAttemptContext);
        return lofrw;
    }
}
