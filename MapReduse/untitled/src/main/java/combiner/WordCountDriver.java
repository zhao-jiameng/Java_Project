package combiner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 驱动类，主要将我们写好的mr封装成一个Job对象，进行提交，然后执行
 */
public class WordCountDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {


    //1.创建配置对象
    Configuration conf=new Configuration();
    //2.创建Job对象
    Job job=Job.getInstance(conf);                  //mapreduce.Job
    //3.关联驱动类
    job.setJarByClass(WordCountDriver.class);
    //4.设置Mapper 和 Reducer类
    job.setMapperClass(WordCountMapper.class);
    job.setReducerClass(WordCountReducer.class);
    //5.设置Mapper输出的key和value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
    //6.设置最终输出的key和value的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
    //7.设置输入和输出路径
        FileInputFormat.setInputPaths(job,new Path("G:\\hadoop从入门到入狱.txt"));         //mapreduce.lib.input.FileInputFormat
        FileOutputFormat.setOutputPath(job,new Path("G:\\output"));

        //设置使用combiner
        job.setCombinerClass(WordCountCombiner.class);
     //8.提交Job
        job.waitForCompletion(true);
        }
}