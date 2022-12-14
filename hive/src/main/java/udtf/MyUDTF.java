package udtf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;

/**
 * 需要继承GenericUDTF
 * 需求：将给定的字符串按照分隔符分割，返回多行函数
 */
public class MyUDTF extends GenericUDTF {
    ArrayList<String> outwords = new ArrayList<>();
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {     //约束类型
        //约束函数返回列的名字
        ArrayList<String> filedNames = new ArrayList<>();
        //约束函数返回的类型
        ArrayList<ObjectInspector> fileOis=new ArrayList<>();
        filedNames.add("word");
        fileOis.add(PrimitiveObjectInspectorFactory.javaIntObjectInspector);
        return ObjectInspectorFactory.getStandardStructObjectInspector(filedNames,fileOis);

    }

    /**
     *函数的核心处理方法
     * @param objects   传入函数参数
     * @throws HiveException
     */
    @Override
    public void process(Object[] objects) throws HiveException {
        //获取待处理数据
        String data = objects[0].toString();
        //获取分隔符
        String split = objects[1].toString();
        //分割
        String[] split1 = data.split(split);
        //写出
        for (String word: split1) {
            outwords.clear();
            outwords.add(word);
            forward(outwords);
        }
    }

    @Override
    public void close() throws HiveException {

    }
}
