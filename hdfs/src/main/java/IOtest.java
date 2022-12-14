import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

public class IOtest {
    private URI uri=null;
    private Configuration conf=null;
    private String user=null;
    private FileSystem fs;
    public void init() throws IOException, InterruptedException {
        //创建文件系统对象
        URI uri = URI.create("hdfs://hadoop101:8020");
        Configuration conf = new Configuration();
        //conf.set("配置项名称","值");
        String user = "root";
        FileSystem fs = FileSystem.get(uri, conf, user);
    }
    @Test
    public void testIOUpdate() throws IOException {         //上传文件
        //待上传的文件
        String srcFile="G:\\hadoop从入门到入狱.txt";
        //目标文件
        String destFile="/hadoop从入门到入狱";
        //输入流
        FileInputStream fis=new FileInputStream(new File(srcFile));
        //输出流
        FSDataOutputStream fos = fs.create(new Path(destFile));
        //流的对拷贝
//        int i;
//        byte [] buffer=new byte[1024];                        //创建缓冲区
//        while ((i=fis.read(buffer))!=-1){
//            fos.write(buffer,0,i);
            //while ((i=fis.read())!=-1){                     //一个字节一个字节
            // fos.write(i);
        //}
        //使用工具
        IOUtils.copyBytes(fis,fos,conf);                     //等同上
        IOUtils.closeStream(fis);                            //关闭流
        IOUtils.closeStream(fos);
    }
    @Test
    public void testIODownload() throws IOException {       //下载文件
        //待下载的文件
        String srcFile="/hadoop从入门到入狱";
        //目标文件
        String destFile="G:\\hadoop从入门到入狱";
        //输入流
        FSDataInputStream fis = fs.open(new Path(srcFile));
        //输出流
        FileOutputStream fos = new FileOutputStream(new File(destFile));
        //流的对拷贝
        IOUtils.copyBytes(fis,fos,conf);                    //工具类
        //流的关闭
        IOUtils.closeStream(fis);                          //关闭流
        IOUtils.closeStream(fos);
    }
}
