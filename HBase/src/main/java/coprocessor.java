import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.coprocessor.*;
import org.apache.hadoop.hbase.wal.WALEdit;

import java.io.IOException;
import java.util.Optional;

public class coprocessor extends BaseRegionObserver   {
//    public class coprocessor implements RegionObserver, RegionCoprocessor {
//        @Override
//        public Optional<RegionObserver> getRegionObserver() {
//             return Optional.of(this);
//        }

    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> c, Put put, WALEdit edit) throws IOException {
        //1、获取配置信息
        Configuration entries = HBaseConfiguration.create();
        entries.set("hbase.zookeeper.quorum","hadoop102,hadoop103,hadoop104");
        //2、获取连接
        Connection connection = ConnectionFactory.createConnection(entries);
        //3、获取表对象
        Table test = connection.getTable(TableName.valueOf("test"));
        //4、插入数据
        test.put(put);
        //5、释放资源
        test.close();
        connection.close();
    }
}
