package 压缩;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;

import java.io.*;
//数据流压缩
public class TestCompress {
    /**
     * 使用支持压缩的输出流将数据写到文件中
     */
    @Test
     public void testCompress() throws IOException, ClassNotFoundException {
        //待压缩的文件
        String srcFile="G:\\liunx\\sed命令详解.docx";
        //压缩后的文件
        String destFile="G:\\liunx\\sed命令详解.docx";      //使用codec.getDefaultExtension()获取压缩后缀
        //输入流
        FileInputStream fileInputStream = new FileInputStream(new File(srcFile));
        //输出流
        //使用的压缩编解码器
        String  classname="org.apache.hadoop.io.compress.DefaultCodec";
        Class<?> classCodec = Class.forName(classname);
        Configuration conf = new Configuration();
        CompressionCodec codec =                               //获取编解码器对象
                (CompressionCodec) ReflectionUtils.newInstance(classCodec, conf);
        FileOutputStream out = new FileOutputStream
                (new File(destFile + codec.getDefaultExtension()));//输出流对象，并获取压缩后缀名
        CompressionOutputStream codesout = codec.createOutputStream(out);//支持编码的out
        //读写
        IOUtils.copyBytes(fileInputStream,codesout,conf);
        //关闭
        IOUtils.closeStream(fileInputStream);
        IOUtils.closeStream(codesout);
    }

    /**
     * 使用支持压缩的输入流将数据从文件中读入
     */
    @Test
    public  void testDeCompress() throws IOException {
        //待解压的文件
        String srcFile="G:\\liunx\\sed命令详解.docx.deflate";
        //解压后的文件
        String descFile="G:\\liunx\\sed命令详解.docx";
        //输入流
        Configuration conf = new Configuration();
        CompressionCodec codec =
                new CompressionCodecFactory(conf).getCodec(new Path(srcFile));//根据文件后缀获取对应编解码器
        FileInputStream in=new FileInputStream(new File(srcFile));
        CompressionInputStream codecin = codec.createInputStream(in);//支持压缩的输入流
        //输出流
        FileOutputStream out = new FileOutputStream(new File(descFile));
        //读写
        IOUtils.copyBytes(codecin,out,conf);
        //关闭
        IOUtils.closeStream(codecin);
        IOUtils.closeStream(out);
    }
}
