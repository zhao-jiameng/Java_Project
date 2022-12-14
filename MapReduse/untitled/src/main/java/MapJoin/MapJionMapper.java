package MapJoin;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MapJionMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
    private Map<String,String> pdMap=new HashMap<>();
    private Text outk=new Text();
    @Override
    protected void setup(Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
        //计数器
        context.getCounter("组名","计数器名").increment(1);//步长（自增长度）

        //将小表的数据缓存到内存中
        //获取缓存数据
        URI[] cacheFiles = context.getCacheFiles();
        URI file=cacheFiles[0];
        //通过IO读取文件
        FileSystem fs = FileSystem.get(context.getConfiguration());//获取文件系统对象
        FSDataInputStream open = fs.open(new Path(file));       //创建输入流
        BufferedReader br=new BufferedReader(                   //转换流 (注意编码      字节-->字符
                new InputStreamReader(open,"utf-8")
        );
        String line;
        while ((line=br.readLine())!=null){                      //逐行读取
            String[] split = line.split("\t");              //处理每行数据
            pdMap.put(split[0],split[1] );
        }
        IOUtils.closeStream(br);                                //关闭流
        fs.close();


    }

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
        //正常处理大表数据
        String line= value.toString();
        String[] split = line.split("\t");
        split[1]=pdMap.get(split[1]);                   //join
        outk.set(split[0]+"\t"+split[1]+"\t"+split[2]); //封装key
        context.write(outk,NullWritable.get());
    }
}
