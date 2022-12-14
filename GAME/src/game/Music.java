package game;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.InputStream;


public class Music implements Runnable {       //音乐类
    Player player=null;
    @Override
    public void run() {
        InputStream resourceAsStream = this.getClass().getResourceAsStream("2.mp3");
        try {
            player=new Player(resourceAsStream);
            player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }

    }
    public void stopBGM(){
    if (player!=null)
            player.close();

    }
}
