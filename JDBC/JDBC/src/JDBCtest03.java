import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCtest03 {
    public static void main(String[] args) {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;

        try {
            conn=JDBCUtil.getConnection();
            stmt=conn.createStatement();
            String sql="select * from class";
            rs=stmt.executeQuery(sql);
            while (rs.next()){
                System.out.println("姓名："+rs.getString("name")+"   年龄："+rs.getInt(2)+"   班级："+rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn,stmt,rs);
        }

    }
}
