package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ground {   //地面类
    public BufferedImage image; //存放地面图片
    public int x;
    public int y;

    public Ground() {

        try {
            x=0;
            y=500;
            image= ImageIO.read(getClass().getResource("ground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void step(){
        x-=1; //让地面往左运动
        if (x==-100){
            x=0;
        }
    }

}
