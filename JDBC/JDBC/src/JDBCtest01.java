import java.sql.*;
public class JDBCtest01 {
    public static void main(String[] args) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn=JDBCUtil.getConnection();
            String sql="select ? from class";
            ps=conn.prepareStatement(sql);
            ps.setString(1,"name");
            rs=ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn,ps,rs);
        }
    }
}
