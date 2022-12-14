import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCtest02 {
    public static void main(String[] args) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/class","root","123456");//这是一个工具方法
            String sql="select ? from class";   //DQL语句
            ps=conn.prepareStatement(sql);      //编译
            ps.setString(1,"*");     //输入占位符
            rs=ps.executeQuery();       //执行
            while (rs.next()){          //遍历
                String aname=rs.getString(1);
                System.out.println(aname);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn,ps,rs);//释放方法
        }
    }
}
