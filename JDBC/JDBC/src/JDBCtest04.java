import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCtest04 {
    public static void main(String[] args) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {

            conn=JDBCUtil.getConnection(); //这是一个工具方法
            String sql="select * from class where name like ?";   //DQL语句
            ps=conn.prepareStatement(sql);      //编译
            ps.setString(1,"zjm");
           // ps.setString(2,"zjm");//输入占位符
            rs=ps.executeQuery();       //执行
            if(rs.next()){
                System.out.println(rs.getString(1));
               // System.out.println(rs.getString(2));//遍历
                System.out.println("成功");

            }else{
                System.out.println("失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn,ps,rs);//释放方法
        }
    }
}