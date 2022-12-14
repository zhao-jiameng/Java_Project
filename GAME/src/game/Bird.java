package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bird {
    public BufferedImage images[];
    public BufferedImage image;     //存放小鸟图片
    public int x;
    public int y;
    public int width;
    public int height;
    public int index=0;
    public double speed=0;      //小鸟初始速度
    public double upspeed=30;   //初始上抛速度
    public double s=0;          //经过t，发生的位移
    public double t=0.2;        //发生位移时间
    public double g=9.8;        //重力加速度
    public Bird() throws IOException {
        x=120;
        y=120;
        images=new BufferedImage[8];
        image= ImageIO.read(getClass().getResource("0.png"));
        width=image.getWidth();
        height=image.getHeight();
        for (int i=0;i<images.length;i++) {
           images[i] = ImageIO.read(getClass().getResource(i+".png"));
       }
    }


    public void fly(){          //小鸟飞飞
        index++;
        image=images[index/2%8];
    }
    public void upSpeed(){      //鼠标点击游戏屏幕，给小鸟一个初始上抛速度
        speed=upspeed;
    }
    public void distanceChange(){   //实现小鸟速度，位移，纵坐标变化
        double v=speed; //初始速度
        s=v*t-g*t*t/2;  //经过t小鸟的位移
        speed=v-g*t;    //小鸟经过时间t的末速度
        y=y-(int)s;          //经过时间t后，小鸟的y

    }
}
