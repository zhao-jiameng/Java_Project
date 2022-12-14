package partitioner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 自定义Mapper的开发
 * 继承hadoop提供的Mapper类，提供输入输出kv的类型，重写map方法
 * mapper类的四个泛型
 *  KEYIN:输入数据key的类型            LongWritable（表示偏移量）
 *  VALUEIN:输入数据value的类型        Test
 *
 *  KEYOUT:输出数据key的类型           Test
 *  VALUEOUT:输出数据value的类型       IntWritable
 *
 */
public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    private Text outk=new Text();
    private IntWritable outv=new IntWritable(1);
    /**
     * map方法是整个mr中map阶段的核心处理方法
     * @param key           表示偏移量
     * @param value         读取到一行数据
     * @param context       上下文对象，负责调度整个Mapper类中方法的执行
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        // 1.将读取到的数据转回String（方便操作）
        String line=value.toString();
        //2、按照分隔符分割当前的数据
        String [] words=line.split(" ");
        //3、将words进行迭代处理，吧每个单词拼为kv写出
        for (String word:words) {
            outk.set(word);
            //写出
            context.write(outk,outv );
        }
    }
}
