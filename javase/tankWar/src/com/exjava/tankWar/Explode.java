package com.exjava.tankWar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

//爆炸类(爆炸的英文：explode)
public class Explode {

    int x, y;
    private TankClient tc;

    private boolean live = true;

    private boolean init = false; //定义变量表示图片是否已经被加载到内存了

    private static Toolkit tk = Toolkit.getDefaultToolkit(); //Toolkit是java.awt中的工具包类，这样就可以方便的拿到硬盘的信息

    private static Image[] imgs = { //每次只需加载一次，所以写出static
            //下面用到了反射的概念，Explode.class.getClassLoader()是得到最终生成class文件的装载器，getResource()得到装载器的目录
            //此时加载的图片路径绝对和相对都不合适，用这个方法最合适，这个方法比较常用
            tk.getImage(Explode.class.getClassLoader().getResource("images/1.gif")),
            tk.getImage(Explode.class.getClassLoader().getResource("images/2.gif")),
            tk.getImage(Explode.class.getClassLoader().getResource("images/3.gif")),
            tk.getImage(Explode.class.getClassLoader().getResource("images/4.gif")),
            tk.getImage(Explode.class.getClassLoader().getResource("images/5.gif")),
            tk.getImage(Explode.class.getClassLoader().getResource("images/6.gif")),
            tk.getImage(Explode.class.getClassLoader().getResource("images/7.gif")),
            tk.getImage(Explode.class.getClassLoader().getResource("images/8.gif")),
            tk.getImage(Explode.class.getClassLoader().getResource("images/9.gif")),
            tk.getImage(Explode.class.getClassLoader().getResource("images/10.gif"))
    };

    int step = 0; //定义变量代表画爆炸的圆画到第几个了

    public Explode(int x, int y, TankClient tc) {
        this.x = x;
        this.y = y;
        this.tc = tc;
    }

    public void draw(Graphics g) {

        if (!init) {
            for (int i = 0; i < imgs.length; i++) {
                g.drawImage(imgs[i], -100, -100, null); //把图片画在看不到的地方
            }
            init = true;
        }

        if (!live) {
            tc.explodes.remove(this);
            return;
        }

        if (step == imgs.length) {
            live = false;
            step = 0;
            return;
        }

        g.drawImage(imgs[step], x, y, null);

        step++;

    }

}
