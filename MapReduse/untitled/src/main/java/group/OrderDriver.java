package group;

import combiner.WordCountCombiner;
import combiner.WordCountDriver;
import combiner.WordCountMapper;
import combiner.WordCountReducer;
import org.apache.commons.io.input.NullReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class OrderDriver{
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf=new Configuration();
        //2.创建Job对象
        Job job=Job.getInstance(conf);                  //mapreduce.Job
        //3.关联驱动类
        job.setJarByClass(OrderDriver.class);
        //4.设置Mapper 和 Reducer类
        job.setMapperClass(OrderMapper.class);
        job.setReducerClass(OrderReduce.class);
        //5.设置Mapper输出的key和value类型
        job.setMapOutputKeyClass(OrderBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        //6.设置最终输出的key和value的类型
        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);
        //7.设置输入和输出路径
        FileInputFormat.setInputPaths(job,new Path("G:\\test\\订单.txt"));         //mapreduce.lib.input.FileInputFormat
        FileOutputFormat.setOutputPath(job,new Path("G:\\output"));

        //设置使用combiner
        job.setGroupingComparatorClass(OrderGroupComparator.class);
        //8.提交Job
        job.waitForCompletion(true);
    }
}
