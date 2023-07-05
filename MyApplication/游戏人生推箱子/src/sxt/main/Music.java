package sxt.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

/**
 * 游戏背景音乐
 */
public class Music {
    private static AudioClip fly;  //wav类型

    public static void load(){
        try {
            fly = Applet.newAudioClip(new File("music/mc.wav").toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void playFly(){
        //重复播放
        fly.loop();
    }
}
