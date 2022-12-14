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
 * 需求：将给定的字符串按照分隔符分割，返回多行多列函数
 */
public class MyUDTF2 extends GenericUDTF {
    ArrayList<String> outwords = new ArrayList<>();
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {     //约束类型
        //约束函数返回列的名字
        ArrayList<String> filedNames = new ArrayList<>();
        //约束函数返回的类型
        ArrayList<ObjectInspector> fileOis=new ArrayList<>();
        filedNames.add("name");
        filedNames.add("desc");
        fileOis.add(PrimitiveObjectInspectorFactory.javaIntObjectInspector);
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
        //获取行分隔符
        String rowsplit = objects[1].toString();
        //获取列分隔符
        String colsplit= objects[2].toString();
        //分割多行
        String[] rows = data.split(rowsplit);
        //迭代每行，分割逐行写出
        for (String row: rows) {
            //分割
            String[] col = row.split(colsplit);
            outwords.clear();
            outwords.add(col[0]);
            outwords.add(col[1]);
            forward(outwords);
        }
    }

    @Override
    public void close() throws HiveException {

    }
}
