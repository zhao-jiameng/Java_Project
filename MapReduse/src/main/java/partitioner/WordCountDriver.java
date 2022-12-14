package partitioner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import javax.xml.transform.Result;
import java.io.IOException;

/**
 * 驱动类，主要将我们写好的mr封装成一个Job对象，进行提交，然后执行（Yarn集群的客户端）
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
        //8.设置分区类
        job.setPartitionerClass(myPartitioner.class);
        //9.设置reduce个数
        job.setNumReduceTasks(3);

        //自行删除上次输出目录
        FileSystem fileSystem = FileSystem.get(conf);
        Path outputPath = new Path("G:\\output");
        if(fileSystem.exists(outputPath)){
            fileSystem.delete(outputPath,true);
        }

        //10.提交Job(是否输出日志)
        boolean resultejob=job.waitForCompletion(true);
        System.exit(resultejob?0:1);
        }
}