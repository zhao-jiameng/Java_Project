package MapJoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;


import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



import java.io.IOException;
import java.net.URI;

public class MapJoinDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf=new Configuration();                 //创建配置对象
//        conf.set("fs.defaultFS","hdfs://hadoop101:8020");       //设置HDFS NameNode 的地址
//        conf.set("mapreduce.framework.name","yarn");            //设置MapReduce运行在Yarn上
//        conf.set("mapreduce.app-submission.cross-platform","true"); //设置MApReduce可以在远程集群运行
//        conf.set("yarn.resourcemanager.hostname","hadoop102");       //指定 Yarn resourcemanager的位置
        conf.set("mapreduce.job.queuename","zjm");       //配置提交队列
        Job job=Job.getInstance(conf);                          //创建job对象
        job.setJarByClass(MapJoinDriver.class);                    //关联驱动类
        job.setMapperClass(MapJionMapper.class);                   //关联mapper类
        job.setMapOutputKeyClass(Text.class);                   //map输出的类型
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(Text.class);                      //最终输出类型
        job.setOutputValueClass(NullWritable.class);
        FileInputFormat.setInputPaths(job,new Path("args[0]"));       //输入输出路径
        FileOutputFormat.setOutputPath(job,new Path("args[1]"));
        job.setNumReduceTasks(0);           //设置reduce为空
        job.addCacheFile(URI.create("file:///G:/test/jointext/pd.txt"));  //设置缓存文件
        job.waitForCompletion(true);                    //提交
    }
}
