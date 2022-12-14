package reducejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ReduceJoinMapper extends Mapper<LongWritable, Text,Text,OrderPdBean> {

    private String currentFileName;
    private Text outk=new Text();
    private OrderPdBean outv=new OrderPdBean();
    /**
     * 在maptask开始前调用一次
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Mapper<LongWritable, Text, Text, OrderPdBean>.Context context) throws IOException, InterruptedException {
        //获取当前切片对象
        InputSplit inputSplit = context.getInputSplit();
        FileSplit inputSplit1 = (FileSplit) inputSplit;
        currentFileName=inputSplit1.getPath().toString();          //获取当前文件路径
    }

    /**
     * 两个文件：
     *  order.txt   订单数据
     *  pd.txt      商品数据
     *  通过切片分析处理那个文件
     */
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, OrderPdBean>.Context context) throws IOException, InterruptedException {
        //获取第一行数据
        String line=value.toString();
        //切割数据
        String [] split=line.split("\t");
        //判断文件      //封装
        if (currentFileName.contains("order")){
            //订单数据      1001 01 1
            outk.set(split[1]);
            outv.setOrderId(split[0]);
            outv.setPid(split[1]);
            outv.setAmount(Integer.parseInt(split[2]));
            outv.setPname("");
            outv.setTitle("order");

        }else {
            outk.set(split[0]);
            outv.setOrderId("");
            outv.setPid(split[0]);
            outv.setAmount(0);
            outv.setPname(split[1]);
            outv.setTitle("pd");
        }
        //写出
        context.write(outk,outv);
    }
}
