package outputformat;

import group.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class LogDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf=new Configuration();
        //2.创建Job对象
        Job job=Job.getInstance(conf);                  //mapreduce.Job
        //3.关联驱动类
        job.setJarByClass(LogDriver.class);
        //4.设置Mapper 和 Reducer类
        job.setMapperClass(LogMapper.class);
        job.setReducerClass(LogReducer.class);
        //5.设置Mapper输出的key和value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        //6.设置最终输出的key和value的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        //7.设置输入和输出路径
        FileInputFormat.setInputPaths(job,new Path("E:\\test.log.txt"));         //mapreduce.lib.input.FileInputFormat
        FileOutputFormat.setOutputPath(job,new Path("E:\\output"));
        //设置自定义OutputFormatClass
        job.setOutputFormatClass(LogOutputFormat.class);
        //8.提交Job
        job.waitForCompletion(true);
    }
}
