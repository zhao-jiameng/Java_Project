package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Column {       //管道类
    public BufferedImage cImage;
    public int x;
    public  int y;
    public int width;
    public int height;
    public  int distance=270;   //两根管道之间的距离
    public static  int count=0;
    Random random = new Random();
    public Column() throws IOException {
        cImage= ImageIO.read(getClass().getResource("column.png"));
        x=450+distance*count;
        width=cImage.getWidth();    //获得管道的宽
        height=cImage.getHeight();  //高
        y=-( height/2-random.nextInt(300)-50);


        count++;
    }
    public void step(){
        x-=5; //让地面往左运动
        if (x<=-width/2){
            x=x+distance*2;
            y=-(height/2-random.nextInt(300)-50) ;
            //x=400;
        }
    }
}
