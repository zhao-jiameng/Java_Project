import java.sql.*;

public class JDBCUtil {
    /**
     * 工具类构造方法私有化
     */
    private JDBCUtil() {
    }

    static {                                              //注册驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return 数据库连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {     //获取数据库连接对象
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/class","root","123456");
        // return DriverManager.getConnection(url, user, password);
    }

    /**
     * @param conn 连接对象
     * @param ps   数据库操作对象
     * @param rs   结果集
     */
    public static void close(Connection conn, Statement ps, ResultSet rs) {   //释放资源
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
}