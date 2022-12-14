import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.NamespaceExistException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;


/**
 * 1、创建命名空间
 * 2、判断表是否存在
 * 3、创建表
 * 4、删除表
 */
public class HBaseDDL {
    //声明connection和admin
    private static Connection connection;
    private static Admin admin;
    static{
        //1、创建配置信息，并指定集群
        Configuration entries = HBaseConfiguration.create();
        entries.set("hbase.zookeeper.quorum","hadoop102,hadoop103,hadoop104");
        //2、创建连接器
        try {
            connection = ConnectionFactory.createConnection(entries);
            //3、创建DDl操作对象
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void close() throws IOException {
        admin.close();
        connection.close();
    }
    //TODO 创建命名空间
    public static void cerateNS(String ns) throws IOException {
        //4、创建命名空间描述器
        NamespaceDescriptor.Builder builder = NamespaceDescriptor.create(ns);//创建builder对象
        NamespaceDescriptor namespaceDescriptor = builder.build();          //返回命名空间描述器，可以加参数
        //5、创建命名空间
        try {   //ctrl+alt+t 快速环绕
            admin.createNamespace(namespaceDescriptor);
        } catch (NamespaceExistException e) {
            System.out.println("命名空间已存在");
        }
        //6、释放连接
        close();
    }
    //TODO 判断表是否存在
    public static boolean isTableExist(String tableName) throws IOException {
        return admin.tableExists(TableName.valueOf(tableName));
    }
    //TODO 创建表
    public static void creatTable(String tableName,String...cfs) throws IOException {  //...可变参数，可以不传
        //1、判断是否有列族信息
        if (cfs.length<=0){
            System.out.println("请输入列族信息！！！");
            return;
        }
        //2、判断表是否存在
        if (isTableExist(tableName)){
            System.out.println("该表已存在！！！");
            return;
        }
        //3、创建表描述器Builder对象
        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));
        //4、循环放入列族信息
        for (String cf : cfs) {
            //5、创建列族描述器
                //ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cf)).setMaxVersions(5);    修改列族信息
            ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cf)).build();
            //6、放入列族信息
            tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptor);
        }
        //7、创建表描述器
        tableDescriptorBuilder.setCoprocessor("coprocessor");
        TableDescriptor build = tableDescriptorBuilder.build();
        //8、创建表
        admin.createTable(build);
        //9、释放空间
        close();
    }
    //TODO 删除表
    public static boolean dropTable(String tableName) throws IOException {
        // 1. 判断表格是否存在
        if (!isTableExist(tableName)){
            System.out.println("表格不存在  无法删除");
            return false;
        }
        TableName name = TableName.valueOf(tableName);
        //1、使表不可用
        admin.disableTable(name);
        //2、删除表
        admin.deleteTable(name);
        return true;
    }
    //TODO 修改表
    /**
     * 修改表格中一个列族的版本
     * @param namespace 命名空间名称
     * @param tableName 表格名称
     * @param columnFamily 列族名称
     * @param version 版本
     * */
    public  static  void modifyTable(String  namespace  ,String tableName,String columnFamily,int version) throws IOException {
        // 判断表格是否存在
        if (!isTableExist(tableName)){
            System.out.println("表格不存在无法修改");
            return;
        }
        try {
            // 2. 调用方法修改表格
                 // 2.0 获取之前的表格描述
            TableDescriptor  descriptor= admin.getDescriptor(TableName.valueOf(namespace, tableName));
                // 2.1 创建一个表格描述建造者
                // 如果使用填写 tableName 的方法  相当于创建了一个新的表格描述建造者 没有之前的信息
                // 如果想要修改之前的信息  	必须调用方法填写一个旧的表格描述
            TableDescriptorBuilder tableDescriptorBuilder= TableDescriptorBuilder.newBuilder(descriptor);
            // 2.2 对应建造者进行表格数据的修改
            ColumnFamilyDescriptor columnFamily1=descriptor.getColumnFamily(Bytes.toBytes(columnFamily));
            // 创建列族描述建造者

            // 需要填写旧的列族描述
            ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder  = ColumnFamilyDescriptorBuilder.newBuilder(columnFamily1);
            // 修改对应的版本
            columnFamilyDescriptorBuilder.setMaxVersions(version);
            //此处修改的时候  如果填写的新创建   那么别的参数会初始化
            tableDescriptorBuilder.modifyColumnFamily(columnFamilyDescriptorBuilder.build ());
            admin.modifyTable(tableDescriptorBuilder.build ());
        } catch (IOException e) {e.printStackTrace();}
    //3、释放空间
        close();
    }
}