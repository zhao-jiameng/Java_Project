package combiner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 自定义Reducer的开发
 * 继承hadoop提供的Reducer类，重写reduce方法,指定四个泛型
 *  KEYIN:      输入数据key的类型          Test                对应map输出
 *  VALUEIN:    输入数据value的类型        IntWritable         表示一个单词
 *
 *  KEYOUT:     输出数据key的类型          Test
 *  VALUEOUT:   输出数据value的类型        IntWritable         表示单词出现总次数
 *
 */
public class WordCountReducer extends Reducer<Text, IntWritable,Text, IntWritable> {
    //定义输出的value
    private IntWritable outv=new IntWritable();
    /**
     * 多个相同key的kv对会组成一组KV对，一组相同的kv对会执行一次reduce方法
     * @param key       输入数据的key，表示一个单词
     * @param values    迭代器，代表一个单词出现多少次（当前key对应的所有value）
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        //1.迭代values，取出每一个value进行汇总
        int sum=0;
        for (IntWritable value:values
             ) {
            sum+=value.get();
        }
        //2.封装value
        outv.set(sum);
        //3.写出
        context.write(key,outv);
    }
}
