package udf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.Text;

/**
 * 自定义UDF函数需要继承GenericUDF
 * 需求：将传入函数中的字符串，计算长度并返回
 */
public class StringLengthUDF extends GenericUDF {
    /**
     * 完成初始化工作
     * @param objectInspectors  传入到函数中参数类型的鉴别器对象
     * @return
     * @throws UDFArgumentException
     */
    @Override
    public ObjectInspector initialize(ObjectInspector[] objectInspectors) throws UDFArgumentException {
        //基本判断
        if (objectInspectors.length!=1){    //判断传输函数的参数个数是否合法
            throw new UDFArgumentLengthException("Input Args Length Error!!!");
        }
        if (!objectInspectors[0].getCategory().equals(ObjectInspector.Category.PRIMITIVE)){ //判断参数类型
            throw new UDFArgumentTypeException(0,"Input Args Type Error!!!");
        }
        //约定函数返回值类型
        return PrimitiveObjectInspectorFactory.javaIntObjectInspector;  //基本类工厂类.java int类
    }

    /**
     * 函数核心处理方法
     * @param deferredObjects   传入到函数中的实际参数
     * @return
     * @throws HiveException
     */
    @Override
    public Object evaluate(DeferredObject[] deferredObjects) throws HiveException {
        return deferredObjects[0].get().toString().length();
    }

    /**
     * 获取一个字符串
     * @param strings
     * @return
     */
    @Override
    public String getDisplayString(String[] strings) {
        return null;
    }
}
