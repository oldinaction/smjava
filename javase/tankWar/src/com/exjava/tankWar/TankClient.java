package com.exjava.tankWar;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List; //java.awt包中也有个List，所以此处要导包明确
import java.util.ArrayList;
import java.util.Properties;

/**
 * 主要的类
 *
 * @author oldinaction
 * @ClassName: TankClient
 * @Description: 坦克，子弹，爆炸，血块都在这里实例化
 * @date 2014年9月2日 下午4:15:44
 */
public class TankClient extends Frame {
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

    Tank myTank = new Tank(350, 520, true, Direction.STOP, this); //我方坦克
    Wall w1 = new Wall(100, 300, 20, 100, this), w2 = new Wall(300, 250, 150, 20, this);
    Blood b = new Blood(this);

    List<Missile> missiles = new ArrayList<Missile>(); //定义一个集合来装子弹
    List<Explode> explodes = new ArrayList<Explode>();
    List<Tank> tanks = new ArrayList<Tank>();

    Image offScreenImage = null; //定义一个屏幕后的虚拟图片

    @Override
    public void paint(Graphics g) {
        if (tanks.size() <= 0) {
            for (int i = 0; i < Integer.parseInt(ProperMgr.getProperty("reProduceTank")); i++) {
                tanks.add(new Tank(50 + 40 * (i + 1), 50, false, Direction.D, this));
            }
        }

        g.drawString("missiles Count: " + missiles.size(), 10, 50); //用来记录missiles中子弹的个数
        g.drawString("explodes Count: " + explodes.size(), 10, 70);
        g.drawString("tanks Count: " + tanks.size(), 10, 90);
        g.drawString("myTank life: " + myTank.getLife(), 10, 110);

        for (int i = 0; i < missiles.size(); i++) { //遍历集合，把其中的子弹画出来
            Missile m = missiles.get(i);
            m.hitTanks(tanks);
            m.hitTank(myTank);
            m.hitWall(w1); //检测子弹是否撞墙
            m.hitWall(w2);
            m.drawMissile(g);
        }

        for (int i = 0; i < explodes.size(); i++) {
            Explode e = explodes.get(i);
            e.draw(g);
        }

        for (int i = 0; i < tanks.size(); i++) {
            Tank t = tanks.get(i);
            t.collidesWithWall(w1); //检测敌方坦克是否撞墙
            t.collidesWithWall(w2);
            t.collidesWithTanks(tanks);
            t.drawTank(g);
        }

        myTank.drawTank(g);
        myTank.eat(b);
        w1.draw(g);
        w2.draw(g);
        b.draw(g);
    }

    //利用双缓冲消除圆圈移动时屏幕的闪动
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT); //判断是为了避免每次重画时都给offScreenImage赋值
        }
        Graphics gOffScreen = offScreenImage.getGraphics(); //定义虚拟图片上的画笔gOffScreen
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT); //重画背景，如果没有这句则在屏幕上会保留圆圈的移动路径
        gOffScreen.setColor(c);
        paint(gOffScreen); //把圆圈画到虚拟图片上
        g.drawImage(offScreenImage, 0, 0, null); //再一次性把虚拟图片画到真实屏幕上，在真实屏幕上画则要用真实屏幕的画笔g
    }

    public void luanchFrame() {
        //getProperty返回的是字符串，要将它转成int
        int initTankCount = Integer.parseInt(ProperMgr.getProperty("initTankCount"));

        for (int i = 0; i < initTankCount; i++) {
            tanks.add(new Tank(50 + 40 * (i + 1), 50, false, Direction.D, this));
        }

        this.setLocation(300, 50);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setTitle("坦克大战  - 游戏还存在Bug,欢迎大家试玩！ - 帮助(复活:F2;放弹:1键;超级炮弹:空格) - By:小易 - QQ：381740148");
        this.setResizable(false); //不允许改变窗口大小
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }); //添加关闭功能，此处使用匿名类比较合适
        this.setBackground(Color.BLACK);

        this.addKeyListener(new KeyMonitor());

        setVisible(true);

        new Thread(new PaintThread()).start(); //启动线程，实例化线程对象时不要忘了new Thread(Runnable对象);
    }

    public static void main(String[] args) {
        TankClient tc = new TankClient();
        tc.luanchFrame();
    }

    //PaintThread只为TankClient服务，所以写成内部类好些
    public class PaintThread implements Runnable {

        public void run() {
            while (true) {
                repaint(); //repaint()是TankClient或者他的父类的方法，内部类可以访问外部包装类的成员，这也是内部类的好处
                try {
                    Thread.sleep(50); //每隔50毫秒重画一次
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class KeyMonitor extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }
    }

}














