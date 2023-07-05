package sxt.main;

import sxt.util.GameUtil;

import java.awt.*;

/**
 * 游戏背景类
 */
public class GameBackground {
//    private  Image black;
//    public GameBackground(){
//        black = GameUtil.loadBufferedImage("img/hahaha.png");
//    }

    /**
     * 绘制游戏背景
     */
    public void draw(Graphics g){
        //绘制游戏背景为黑色
        g.setColor(Color.black);
//        g.drawImage(black,0,0,null);
        g.fillRect(0,0,700,700);
    }
}
