package reducejoin;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


import java.io.IOException;

public class ReduceJoinDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf=new Configuration();                 //创建配置对象
        Job job=Job.getInstance(conf);                          //创建job对象
        job.setJarByClass(ReduceJoinDriver.class);                    //关联驱动类
        job.setMapperClass(ReduceJoinMapper.class);                   //关联mr类
        job.setReducerClass(ReduceJoinReduce.class);
        job.setMapOutputKeyClass(Text.class);                   //map输出的类型
        job.setMapOutputValueClass(OrderPdBean.class);
        job.setOutputKeyClass(OrderPdBean.class);                      //最终输出类型
        job.setOutputValueClass(NullWritable.class);
        FileInputFormat.setInputPaths(job,new Path("G:\\test\\jointext"));          //输入输出路径
        FileOutputFormat.setOutputPath(job, new Path("G:\\output"));

        job.waitForCompletion(true);                    //提交
    }
}
