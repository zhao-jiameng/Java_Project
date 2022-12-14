package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BirdGame extends JPanel {     //自定义面板类继承面板类
    ScoreManager sc=new ScoreManager();
    public JPanel jp=new JPanel();
    public BufferedImage bg;                //图片缓冲区（在显示图片前对图片进行操作 eg：.getWidth()宽，.getHeight()高）
    public BufferedImage startbg;
    public BufferedImage overbg;
    public Ground ground;
    public Bird bird;
    public Column columns[];
    public Music music;

    String file="H:\\Java程序\\小程序\\src\\小鸟\\png\\bg.png";

    public int state; //表示游戏状态
    public static final int START=0;    //开始
    public static final int RUNNING=1;  //运行
    public static final int GAMEOVER=2; //结束
    public static int score=0;          //初始积分

    public BirdGame(){
        try {
            state=START;                //游戏初始为游戏开始状态
            ground=new Ground();        //创建地面类对象，调用地面类构造方法
            bird = new Bird();
            columns=new Column[2];
            music = new Music();
            for (int i=0;i<columns.length;i++){
               columns[i]=new Column();
           }
            bg= ImageIO.read(getClass().getResource("bg.png")); //读取这张图片并把图片值赋给变量
            //bg=ImageIO.read(new File(file));
            //bg=ImageIO.read(new File("src/小鸟/png/bg.png"));
            startbg=ImageIO.read(getClass().getResource("start.png"));
            overbg=ImageIO.read(getClass().getResource("gameover.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
     }



    @Override
    public void paint(Graphics g) {                 //绘制一次的画画方法
        super.paint(g);                             //调用画笔
        g.drawImage(bg,0,0,null);                       //绘制背景（最后一个参数为观察者
        switch (state){
            case START:
                //绘制游戏开始图片
                settishi(g);
                g.drawImage(startbg,0,0,null);

                break;
            case RUNNING:
                for (int i=0;i<columns.length;i++) {
                    g.drawImage(columns[i].cImage, columns[i].x, columns[i].y, null);
                }
                break;
            case GAMEOVER:
                //绘制游戏结束图片
                settishi2(g);
                g.drawImage(overbg,0,0,null);

                break;

        }
        g.drawImage(ground.image,ground.x,ground.y,null);   //绘制地面
        g.drawImage(bird.image,bird.x, bird.y,null);        //绘制小鸟

        setScore(g);
    }
    public boolean isHitGround(){           //撞击地面
        if (bird.y+bird.height>500){
            return true;
        }else {
            return false;
        }
    }
    public boolean isHitSky(){           //撞击天空
        if (bird.y<0){
            return true;
        }else {
            return false;
        }
    }
    public boolean isguandao(Column c) {
        if (bird.x + bird.width >= c.x && c.x + c.width >= bird.x) {    //撞击管道左右
           if (bird.y <= c.height / 2 + c.y - 72 || bird.y + bird.height >= c.height / 2 + c.y + 72) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    public void setScore(Graphics g){                             //绘制分数方法
        Font font = new Font(Font.SERIF, Font.ITALIC, 40);  //罗马字体，斜体，40号
        g.setFont(font);        //获取字体
        g.setColor(Color.white);//获取颜色
        g.drawString(score+"分",40,60);       //画字符串
    }
    public void settishi(Graphics g){                             //绘制分数方法
        Font font1 = new Font(Font.SERIF, Font.BOLD, 25);  //罗马字体，斜体，40号
        g.setFont(font1);        //获取字体
        g.setColor(Color.black);//获取颜色
        g.drawString("点击屏幕开始运行",110,400);       //画字符串
        g.drawString("    制作人---赵嘉盟",120,430);
    }
    public void settishi2(Graphics g){                             //绘制分数方法
        Font font2 = new Font(Font.SANS_SERIF, Font.BOLD, 30);  //罗马字体，斜体，40号
        g.setFont(font2);        //获取字体
        g.setColor(Color.red);//获取颜色
        g.drawString("点击屏幕重新开始",100,500);       //画字符串
    }
    public void action() throws InterruptedException {       //游戏对象运动方法
        this.addMouseListener(new BirdMouseListener());     //添加鼠标监听器

        while (true){
            switch (state){         //状态不同，对象运动效果不同
                case START:
                    ground.step();  //调用地面运动方法
                    bird.fly();


                    break;
                case RUNNING:

                    bird.distanceChange();
                    ground.step();  //调用地面运动方法
                    bird.fly();

                    if (isHitGround()||isHitSky()){
                        state=GAMEOVER;
                        break;
                    }
                    for (int i=0;i<columns.length;i++){
                        Column cl=columns[i];
                        cl.step();
                        if (isguandao(cl)){
                           state=GAMEOVER;
                           break;
                        }
                        if (bird.x==cl.x){
                            score++;
                        }
                    }

                    break;
                case GAMEOVER:
                    music.stopBGM();
                    break;
            }
            repaint();  //刷新方法（重新绘制）
            Thread.sleep(50);  //线程睡眠
        }
    }
    class BirdMouseListener extends MouseAdapter{           //小鸟飞行鼠标控制监听内部类
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);

            switch (state){
                case START:
                    state=RUNNING;  //鼠标点击开始运行
                    Thread thread = new Thread(music);
                    thread.start();
                    break;
                case RUNNING:
                    bird.upSpeed(); //鼠标点击屏幕给小鸟一个初始上抛速度
                    break;
                case GAMEOVER:
                    sc.insertScore(score);  //向数据库插入分数
                    List<Score> scores = sc.selectAllScore();//查询数据库所有分数
                    String message="";
                    for (Score score1 : scores) {
                        message=message+"时间："+score1.getTime()+"\n分数："+score1.getScore()+"\n";
                    }
                    JOptionPane.showConfirmDialog(jp,message,"实时分数",JOptionPane.WARNING_MESSAGE);
                    state=START;    //鼠标点击游戏恢复开始状态

                    bird.x=120;
                    bird.y=220;
                    bird.speed=0;
                    Column.count=0;
                    try {
                        columns[0] = new Column();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        columns[1] = new Column();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    score = 0;//给积分初始化
                    for (int i=0;i<columns.length;i++){
                        try {
                            columns[i]=new Column();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    break;

            }
        }
    }
}
