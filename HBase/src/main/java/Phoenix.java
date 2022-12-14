import org.apache.phoenix.queryserver.client.ThinClientUtil;

import java.sql.*;

public class Phoenix {
    public static void main(String[] args) throws SQLException {
        //1、获取连接地址
        String hadoop101 = ThinClientUtil.getConnectionUrl("hadoop101", 8765);
        //2、创建连接
        Connection connection = DriverManager.getConnection(hadoop101);
        //3、预编译SQL
        PreparedStatement preparedStatement = connection.prepareStatement("select 'id','name' from test");
        //4、执行查询
        ResultSet resultSet = preparedStatement.executeQuery();
        //5、解析resultSet
        while (resultSet.next()){
            System.out.println("ID:"+resultSet.getString(1)+
                    ",NAME:"+resultSet.getString(2)+
                    ",ADDR:"+resultSet.getString(3));
        }
        //6、释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
