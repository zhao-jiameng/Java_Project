import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Arrays;


/**
 * 1、新增和修改数据
 * 2、单条数据查询
 * 3、扫描数据
 * 4、删除数据
 */
public class HBaseDML {
    //声明connection对象
    private static Connection connection;
    static{
        //1、创建配置信息，并指定集群
        Configuration entries = HBaseConfiguration.create();
        entries.set("hbase.zookeeper.quorum","hadoop102,hadoop103,hadoop104");
        //2、创建连接器
        try {
            connection = ConnectionFactory.createConnection(entries);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void close() throws IOException {
        connection.close();
    }
    //TODO 新增和修改数据
    public static void putData(String tableName,String rowKey,String cf,String cn,String value) throws IOException  {
        //1、获取DML的table对象
        Table table = connection.getTable(TableName.valueOf(tableName));
        //2、创建put对象
        Put put = new Put(Bytes.toBytes(rowKey));
        //3、给put对象添加数据
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn),Bytes.toBytes(value));
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes("age"),Bytes.toBytes("18"));
        //4、执行操作
        table.put(put);
        //5、释放资源
        table.close();
        close();
    }
    //TODO 单条数据查询
    public static void getData(String tableName,String rowKey,String cf,String cn) throws IOException {
        //1、获取表对象
        Table table = connection.getTable(TableName.valueOf(tableName));
        //2、创建Get对象
        Get get = new Get(Bytes.toBytes(tableName));
        //指定列族
        //  get.addFamily(Bytes.toBytes(cf));
        //指定列族:列
        //  get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
        //3、查询数据
        Result result = table.get(get);
        //4、解析结果集
        for (Cell cell : result.rawCells()) {
            System.out.println("CF："+Bytes.toString(CellUtil.cloneFamily(cell))+
                    "，CN："+Bytes.toString(CellUtil.cloneQualifier(cell))+
                    "，Value："+Bytes.toString(CellUtil.cloneValue(cell)));
        }
        //5、释放资源
        table.close();
        close();
    }
    //TODO 扫描数据
    public static void scanTable(String tableName) throws IOException {
        //1、获取表对象
        Table table = connection.getTable(TableName.valueOf(tableName));
        //2、创建Scan对象
        Scan scan = new Scan();
        //拦截器
        scan.withStartRow(Bytes.toBytes("1001"));
        scan.withStopRow(Bytes.toBytes("1004"),true);//包含，默认不包含
        //3、扫描全表
        ResultScanner results = table.getScanner(scan);
        //4、解析results
        for (Result next : results) {
            Arrays.stream(next.rawCells()).map(cell -> "CF：" + Bytes.toString(CellUtil.cloneFamily(cell)) +
                    "，CN：" + Bytes.toString(CellUtil.cloneQualifier(cell)) +
                    "，Value：" + Bytes.toString(CellUtil.cloneValue(cell))).forEach(System.out::println);
        }
        //5、释放资源
        table.close();
        close();
    }
    //TODO 删除数据
    //DeleteFamily：执行删除整个RowKey的标记，作业范围是当前列族小于等于标记时间戳的数据
    //DeleteColumn:执行删除指定列族：列的标记(带s的方法），作业范围是当前列小于等于标记时间戳的数据
    //Delete：执行删除指定列族：列的标记，作业范围是只作用于标记中所携带的时间戳的数据(单条数据)
    public static void deleteData(String tableName,String rowKey,String cf,String cn) throws IOException {
        //1、获取表对象
        Table table = connection.getTable(TableName.valueOf(tableName));
        //2、创建Delete对象
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        //指定列族进行删除
            //delete.addFamily(Bytes.toBytes(cf));
        //指定列族和列进行删除
            //delete.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn)); //删除最新版本
            //delete.addColumns(Bytes.toBytes(cf), Bytes.toBytes(cn));//删除全部版本
        //3、执行删除操作
        table.delete(delete);
        //4、释放资源
        table.close();
        close();
    }
}
