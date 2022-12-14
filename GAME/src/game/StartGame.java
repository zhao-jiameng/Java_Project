package game;

import javax.swing.*;

public class StartGame {                    //游戏开始类
    public static void main(String[] args) throws InterruptedException {
        JFrame jFrame = new JFrame("进击の小鸟");   //创建窗口对象
        jFrame.setSize(400,600);//窗口大小
        jFrame.setLocationRelativeTo(null); //窗口相对位置
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设定点击关闭结束程序

        BirdGame birdGame = new BirdGame();  //初始化游戏对象类
        jFrame.add(birdGame);                //把创建好的对象加进来
        jFrame.setVisible(true);             //让窗口可视化
        birdGame.action();                   //地面运动方法

    }
}
