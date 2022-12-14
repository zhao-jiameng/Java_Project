package partitioner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


/**
 * 泛型指定为map输出的k和v类型
 */
public class myPartitioner extends Partitioner<Text, IntWritable> {
    /**
     *
     * @param text
     * @param intWritable
     * @param i  reducer个数
     * @return
     */
    @Override
    public int getPartition(Text text, IntWritable intWritable, int i) {
        //1、获取v数量
        String num=text.toString();
        //2.开始分区
        int partitioner;
        if (num.startsWith("")){
            //if(num.startsWish("前缀"，起始位置))     判断是否以特定开头
            partitioner=0;
        }else if (num.startsWith("")){
            partitioner=1;
        }else{
            partitioner=2;
        }
        return partitioner;
    }
}