package sxt.main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * 游戏主类
 */
public class GameFrame extends Frame {
    //实例化游戏背景类
    private GameBackground gameBackground;
    //实例化箱子类
    private GameBox gameBox;
    //实例化小人类
    private GamePeople gamePeople;

    //存放图片的图片
    private BufferedImage buffimg = new BufferedImage(700, 700, BufferedImage.TYPE_4BYTE_ABGR);

    //构造器对游戏进行初始化
    public GameFrame() {
        //窗口大小
        setSize(500, 500);
        //窗口是否可见
        setVisible(true);
        //窗口标题
        setTitle("推箱子");
        //窗口初始化位置
//        setLocation(200, 200);
        setLocationRelativeTo(null);
        //窗口大小不可改变
        setResizable(false);

        //窗口的关闭事件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //启动线程
        new run().start();
        //实例化类
        Instantiation();
        //音乐播放
        Music.load();
        Music.playFly();

        //键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                add(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                minu(e);
            }
        });
    }

    class run extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (gameBox.checkpoint==2){
                    setSize(550, 550);
                }else if (gameBox.checkpoint==3){
                    setSize(600, 450);
                }
            }
        }
    }

    /**
     * 实例化类
     */
    public void Instantiation() {
        gameBackground = new GameBackground();
        gameBox = new GameBox();
        gamePeople = new GamePeople();
    }

    boolean at = true;

    /**
     * 所有要绘制的内容都在此方法中进行绘制
     */
    @Override
    public void update(Graphics g) {
        if (gamePeople.life == 0) {
            String over = "游戏通关";
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑", 1, 60));
            g.drawString(over, 100, 400);
            return;
        }
        if (gameBox.checkpoint == 1) {
            //得到图片
            Graphics graphics = buffimg.getGraphics();
            //绘制游戏背景
            gameBackground.draw(graphics);
            //绘制箱子
            gameBox.draw(graphics, gamePeople, gameBox.checkpoint);
            //绘制小人
            gamePeople.draw(graphics, 250, 250);
            //一次性画出来
            g.drawImage(buffimg, 0, 0, null);
        } else if (gameBox.checkpoint == 2) {
            if (at) {
                gamePeople.aa = true;
                gameBox.behavior.clear();
                at = false;
            }
            //得到图片
            Graphics graphics = buffimg.getGraphics();
            //绘制游戏背景
            gameBackground.draw(graphics);
            //绘制箱子
            gameBox.draw(graphics, gamePeople, gameBox.checkpoint);
            //绘制小人
            gamePeople.draw(graphics, 100, 100);
            //一次性画出来
            g.drawImage(buffimg, 0, 0, null);
        } else if (gameBox.checkpoint == 3) {
            if (!at) {
                gamePeople.aa = true;
                gameBox.behavior.clear();

                at = true;
            }
            //得到图片
            Graphics graphics = buffimg.getGraphics();
            //绘制游戏背景
            gameBackground.draw(graphics);
            //绘制箱子
            gameBox.draw(graphics, gamePeople, gameBox.checkpoint);
            //绘制小人
            gamePeople.draw(graphics, 100, 200);
            //一次性画出来
            g.drawImage(buffimg, 0, 0, null);
        } else if (gameBox.checkpoint == 4) {
            if (at) {
                gamePeople.aa = true;
                at = false;
            }
            String over = "通关成功";
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑", 1, 60));
            g.drawString(over, 100, 400);

        }

    }

    /**
     * 按键方法
     */
    public void add(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                gamePeople.walk(1);
                break;
            case KeyEvent.VK_DOWN:
                gamePeople.walk(2);
                break;
            case KeyEvent.VK_LEFT:
                gamePeople.walk(3);
                break;
            case KeyEvent.VK_RIGHT:
                gamePeople.walk(4);
                break;
            case KeyEvent.VK_L:
                gamePeople.stepBack();
                gameBox.stepBack();
                break;
            case KeyEvent.VK_SPACE:
                gamePeople.reset();
                gameBox.reset();
                break;
            case KeyEvent.VK_Q:
                gameBox.resett();
                gamePeople.aa = true;
                gamePeople.life = 3;
                at = true;
                break;
        }

    }

    /**
     * 抬键方法
     */
    public void minu(KeyEvent e) {

    }

}
