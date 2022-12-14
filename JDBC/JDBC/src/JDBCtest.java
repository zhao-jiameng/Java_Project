import java.sql.*;
public class JDBCtest {


        public static void main(String [] args){
            Connection conn=null;
            Statement stmt=null;
            try{
//注册驱动
                Class.forName("com.mysql.cj.jdbc.Driver");
//获取连接
                conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/class","root","123456");
//获取数据库操作对象
                System.out.println("数据库连接成功");
                stmt=conn.createStatement();
//执行SQL语句
                String sql="update class set name='doudou' where name ='zhangsan'";    //不用分号结尾
                int count=stmt.executeUpdate(sql);
                System.out.println(count==1?"保存成功":"保存失败");
            }catch(SQLException | ClassNotFoundException e){
                e.printStackTrace();
            }finally{
//释放资源
                try{
                    if(stmt!=null){
                        stmt.close();
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
                try{
                    if(conn!=null){
                        conn.close();
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
