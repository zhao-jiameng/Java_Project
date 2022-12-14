package outputformat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 *自定义RecordWriter，继承RecordWriter
 *
 */
public class LogRecordWriter extends RecordWriter<Text, NullWritable> {
   //结果文件
    private String zjmPath="E:\\zjm.log";
    private String otherPath="E:\\other.log";
    //输出流
    private FSDataOutputStream zjmOut;
    private FSDataOutputStream otherOut;
    //文件系统对象
    private FileSystem fs;
    //初始化构造器,获取之前的文件系统对象
    public LogRecordWriter(TaskAttemptContext contest) throws IOException {
        fs=FileSystem.get(contest.getConfiguration());
        zjmOut=fs.create(new Path(zjmPath));
        otherOut=fs.create(new Path(otherPath));
    }
    /**
     * 写出数据方法
     * @param text  待写出的key
     * @param nullWritable  待写出的value
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void write(Text text, NullWritable nullWritable) throws IOException, InterruptedException {
        //判断输出
        String log=text.toString();
        if (log.contains("zjm")){
            zjmOut.writeBytes(log+"\n");
        }else{
            otherOut.writeBytes(log+"\n");
        }
    }

    /**
     * 关闭方法，关闭资源
     * @param taskAttemptContext
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        IOUtils.closeStream(zjmOut);
        IOUtils.closeStream(otherOut);
    }
}
