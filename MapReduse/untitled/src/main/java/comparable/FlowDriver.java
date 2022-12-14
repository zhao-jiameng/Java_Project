package comparable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import writable.FlowBean;
import writable.FlowMapper;
import writable.FlowReducer;

import java.io.IOException;

public class FlowDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf=new Configuration();                 //创建配置对象
        Job job=Job.getInstance(conf);                          //创建job对象
        job.setJarByClass(writable.FlowDriver.class);                    //关联驱动类
        job.setMapperClass(FlowMapper.class);                   //关联mr类
        job.setReducerClass(FlowReducer.class);
        job.setMapOutputKeyClass(FlowBean.class);                   //map输出的类型
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);                      //最终输出类型
        job.setOutputValueClass(FlowBean.class);
        FileInputFormat.setInputPaths(job,new Path("G:\\ll.txt"));          //输入输出路径
        FileOutputFormat.setOutputPath(job, new Path("G:\\ll123456"));
        //设置key的比较器对象
        job.setSortComparatorClass(FlowWritableComparator.class);
        job.waitForCompletion(true);                    //提交
    }
}
