package game;



import jdk.nashorn.internal.objects.annotations.Where;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ScoreManager {     //jdbc连接
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public List<Score> selectAllScore(){        //查询方法
        List<Score> list = new ArrayList<>();

        try {
            String sql="select * from score order by time";
            Connection conn = DriverManager.getConnection("jdbc:mysql://cdb-kthncrwi.bj.tencentcdb.com:10159/flybird?useUnicode=true", "student", "521qianfeng");
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                Score score = new Score();
                score.setSid(resultSet.getString("sid"));
                score.setScore(resultSet.getInt("score"));
                score.setTime(resultSet.getString("time"));
                list.add(score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public int insertScore(int score) {         //插入方法
        int num = 0;
        String sql = "insert into score(sid,score,time) value(?,?,?)";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://cdb-kthncrwi.bj.tencentcdb.com:10159/flybird?useUnicode=true", "student", "521qianfeng");
            PreparedStatement pst = conn.prepareStatement(sql);
            String sid= UUID.randomUUID().toString();        //随机生成id
            pst.setString(1,sid);
            pst.setInt(2,score);
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        //创建时间类型对象
            String time=simpleDateFormat.format(date);
            pst.setString(3,time);
            num=pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
