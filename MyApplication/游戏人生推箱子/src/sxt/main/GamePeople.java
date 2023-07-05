package sxt.main;

import sxt.util.GameUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @create
 */
public class GamePeople {

    //用于记录行为
    public static List behavior;
    public GameBox gameBox;

    //小人生命值
    public int  life=3;

    //矩形
    public Rectangle rect;
    //小人图片
    Image pup;
    Image pdown;
    Image pleft;
    Image pright;
    private static final String PEOPLE_UP = "img/up.png";
    private static final String PEOPLE_DOWN = "img/dowm.png";
    private static final String PEOPLE_LEFT = "img/left.png";
    private static final String PEOPLE_RIGHT = "img/right.png";

    //小人位置
     int  x , y ,x1,y1;
    //小人方向
    public boolean up = false, dowm = false, left = false, right = false;

    //构造器初始化
    public GamePeople() {
        //初始化小人图片
        pup = GameUtil.loadBufferedImage(PEOPLE_UP);
        pdown = GameUtil.loadBufferedImage(PEOPLE_DOWN);
        pleft = GameUtil.loadBufferedImage(PEOPLE_LEFT);
        pright = GameUtil.loadBufferedImage(PEOPLE_RIGHT);

        rect = new Rectangle();
        behavior = new ArrayList();
        gameBox = new GameBox();
    }

    //需要用到的变量
    int b = 0;
    boolean aa=true;

    /**
     * 绘制小人
     */
    public void draw(Graphics g,int x,int y) {
        if (b == 0) {
            //一开始的时候画一个
            g.drawImage(pdown, x, y, null);
            rect(g);
        } else {
            //切换图片
            direction(g);
            rect(g);
        }
        if (aa) {
            this.x=x;
            this.y=y;
            this.x1=x;
            this.y1=y;
            aa=false;
        }
        g.setColor(Color.red);
        String str = "生命值："+life;
        g.setFont(new Font("微软雅黑",1,30));
        g.drawString(str,10,50);
    }

    /**
     * 控制小人方向
     */
    public void walk(int num) {
        switch (num) {
            case 1:
                up = true;
                b = 1;
                y -= 50;
                dowm = left = right = false;
                break;
            case 2:
                dowm = true;
                y += 50;
                b = 1;
                up = left = right = false;
                break;
            case 3:
                left = true;
                x -= 50;
                b = 1;
                up = dowm = right = false;
                break;
            case 4:
                right = true;
                x += 50;
                b = 1;
                up = dowm = left = false;
                break;
        }
        behavior.add(num);
    }

    /**
     * 切换小人图片
     */
    public void direction(Graphics g) {
        if (up) {
            g.drawImage(pup, x, y, null);
        } else if (dowm) {
            g.drawImage(pdown, x, y, null);
        } else if (left) {
            g.drawImage(pleft, x, y, null);
        } else if (right) {
            g.drawImage(pright, x, y, null);
        }
    }

    /**
     * 绘制小人矩形
     */
    public void rect(Graphics g){
        rect.x=this.x;
        rect.y=this.y;
        rect.width=50;
        rect.height=50;
//        g.setColor(Color.red);
//        g.drawRect(rect.x,rect.y,rect.width,rect.height);
    }

    /**
     * 回退小人
     */
public int stepBack(){

    //小人初始化到原来的位置
    x=x1;
    y=y1;

//    //如果集合为空  结束方法
//    if (behavior.size() == 0) return 0;
//
//    int ber = (int) behavior.get(behavior.size() - 1);
//
//    switch (ber){
//        case 1:
//            y+=50;
//            behavior.remove(behavior.size()-1);
//            break;
//        case 2:
//            y-=50;
//            behavior.remove(behavior.size()-1);
//            break;
//        case 3:
//            x+=50;
//            behavior.remove(behavior.size()-1);
//            break;
//        case 4:
//            x-=50;
//            behavior.remove(behavior.size()-1);
//            break;
//    }


    return 0;
}
    /**
     * 游戏重置
     */
    public void reset(){
        this.x=x1;
        this.y=y1;
    }
    /**
     * 重新开始
     */
    public void newReset(){
        this.x=250;
        this.y=250;
    }

}
