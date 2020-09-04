package com.exjava.tankWar;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Tank {
    public static final int XSPEED = 5; //定义常量X轴速度
    public static final int YSPEED = 5;

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;

    public static final int LIFE = 100;

    //定义一个随机数产生器，此时的Random类是java.util.Random，不同于Math中的
    private static Random r = new Random(); //随机数产生器只需要一个，所以定义成静态，防止每次new一个坦克是都会产生一个随机数产生器
    private BloodBar bb = new BloodBar();

    private boolean good; //定义变量说明是我方还是敌方坦克，为true表示我方坦克

    private boolean live = true; //定义变量说明是坦克是否存活

    private int life = LIFE; //设置坦克的生命值为100

    TankClient tc;

    private int x, y; //定义变量画圆圈(坦克)时四边形左上点的x、y左边
    private int oldX, oldY; //定义坦克上个位置的坐标

    private boolean bL = false, bU = false, bR = false, bD = false; //定义变量左上右下的按键是否被按下

    private Direction dir = Direction.STOP; //定义变量坦克的方向
    private Direction ptDir = Direction.U; //定义变量坦克炮筒的方向，起初向上

    private int step = r.nextInt(12) + 3; //定义坦克朝着一个方向移动几步

    private static Toolkit tk = Toolkit.getDefaultToolkit();

    private static Image[] TankImages = null;
    private static Map<String, Image> imgs = new HashMap<String, Image>();

    //静态代码区，这样这个类的class文件被加载，首先执行这里的代码；一条语句也可以写在里面，最适合给一些变量做初始化
    static {
        TankImages = new Image[]{
                tk.getImage(Tank.class.getClassLoader().getResource("images/tankL.gif")),
                tk.getImage(Tank.class.getClassLoader().getResource("images/tankLU.gif")),
                tk.getImage(Tank.class.getClassLoader().getResource("images/tankU.gif")),
                tk.getImage(Tank.class.getClassLoader().getResource("images/tankRU.gif")),
                tk.getImage(Tank.class.getClassLoader().getResource("images/tankR.gif")),
                tk.getImage(Tank.class.getClassLoader().getResource("images/tankRD.gif")),
                tk.getImage(Tank.class.getClassLoader().getResource("images/tankD.gif")),
                tk.getImage(Tank.class.getClassLoader().getResource("images/tankLD.gif"))
        };

        imgs.put("L", TankImages[0]);
        imgs.put("LU", TankImages[1]);
        imgs.put("U", TankImages[2]);
        imgs.put("RU", TankImages[3]);
        imgs.put("R", TankImages[4]);
        imgs.put("RD", TankImages[5]);
        imgs.put("D", TankImages[6]);
        imgs.put("LD", TankImages[7]);
    }

    public Tank(int x, int y, boolean good) {
        this.x = x;
        this.y = y;
        this.good = good;
        this.oldX = x;
        this.oldY = y;
    }

    public Tank(int x, int y, boolean good, Direction dir, TankClient tc) {
        this(x, y, good); //相当于调用上面的构造方法
        this.dir = dir;
        this.tc = tc;
    }

    public void drawTank(Graphics g) {
        if (!live) {
            if (!good) {
                tc.tanks.remove(this);
            }
            return; //如果坦克没有存活就直接返回，不用画坦克了
        }

        if (good) bb.draw(g);

        //根据炮筒的方向，画直线代表炮筒
        switch (ptDir) {
            case L:
                g.drawImage(imgs.get("L"), x, y, null);
                break;
            case LU:
                g.drawImage(imgs.get("LU"), x, y, null);
                break;
            case U:
                g.drawImage(imgs.get("U"), x, y, null);
                break;
            case RU:
                g.drawImage(imgs.get("RU"), x, y, null);
                break;
            case R:
                g.drawImage(imgs.get("R"), x, y, null);
                break;
            case RD:
                g.drawImage(imgs.get("RD"), x, y, null);
                break;
            case D:
                g.drawImage(imgs.get("D"), x, y, null);
                break;
            case LD:
                g.drawImage(imgs.get("LD"), x, y, null);
                break;
        }

        move(); //每次按键都会重画，就会调用drawTank，在这里重画坦克的此时位置
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode(); //得到按键的虚拟码，再和下面的KeyEvent.VK_LEFT等虚拟码比较看是否是某按键
        switch (key) {
            case KeyEvent.VK_F2:
                if (!this.live) {
                    this.live = true;
                    this.life = LIFE;
                }
                break;
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
        }
        locateDraction();

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_1: //按1就发射子弹调用fire方法
                fire(); //只有松开1才能发出子弹
                break;
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
            case KeyEvent.VK_SPACE: //按SPACE就发射子弹调用fire方法
                superFire();
                break;
        }
        locateDraction();
    }

    //通过上右下的按键是否被按下判断坦克要运动的方向
    void locateDraction() {
        if (bL && !bU && !bR && !bD) dir = Direction.L;
        else if (bL && bU && !bR && !bD) dir = Direction.LU;
        else if (!bL && bU && !bR && !bD) dir = Direction.U;
        else if (!bL && bU && bR && !bD) dir = Direction.RU;
        else if (!bL && !bU && bR && !bD) dir = Direction.R;
        else if (!bL && !bU && bR && bD) dir = Direction.RD;
        else if (!bL && !bU && !bR && bD) dir = Direction.D;
        else if (bL && !bU && !bR && bD) dir = Direction.LD;
        else if (!bL && !bU && !bR && !bD) dir = Direction.STOP;
    }

    public void move() {
        oldX = x;
        oldY = y;

        switch (dir) {
            case L:
                x -= XSPEED;
                break;
            case LU:
                x -= XSPEED;
                y -= YSPEED;
                break;
            case U:
                y -= YSPEED;
                break;
            case RU:
                x += XSPEED;
                y -= YSPEED;
                break;
            case R:
                x += XSPEED;
                break;
            case RD:
                x += XSPEED;
                y += YSPEED;
                break;
            case D:
                y += YSPEED;
                break;
            case LD:
                x -= XSPEED;
                y += YSPEED;
                break;
            case STOP:
                break;
        }

        if (this.dir != Direction.STOP) {
            this.ptDir = this.dir;
        }

        //防止坦克出界
        if (x < 0) x = 0;
        if (y < 25) y = 25; //考虑了标题栏的高度
        if (x + Tank.WIDTH > TankClient.GAME_WIDTH) x = TankClient.GAME_WIDTH - Tank.WIDTH;
        if (y + Tank.HEIGHT > TankClient.GAME_HEIGHT) y = TankClient.GAME_HEIGHT - Tank.HEIGHT;

        if (!good) {
            Direction[] dirs = Direction.values(); //把枚举转换成数组
            if (step == 0) {
                int rn = r.nextInt(dirs.length);
                dir = dirs[rn]; //如果移动步数为0就改变方向
                step = r.nextInt(12) + 3;
            }
            step--;

            if (r.nextInt(40) > 37) this.fire();
        }
    }

    public void stay() {
        x = oldX;
        y = oldY;
    }

    //坦克开火，就new一个子弹出来
    private Missile fire() {
        if (!live) return null;

        int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2; //让子弹从坦克中心打出
        int y = this.y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2;
        Missile m = new Missile(x, y, good, ptDir, this.tc);
        tc.missiles.add(m); //每new一个Missile对象就把他装到集合中
        return m; //返回的m，其他地方可调用可不调用
    }

    private Missile fire(Direction dir) {
        if (!live) return null;

        int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2; //让子弹从坦克中心打出
        int y = this.y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2;
        Missile m = new Missile(x, y, good, dir, this.tc);
        tc.missiles.add(m); //每new一个Missile对象就把他装到集合中
        return m; //返回的m，其他地方可调用可不调用
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, WIDTH, HEIGHT); //得到坦克的探测方块，Rectangle是java.awt包中专门用于游戏碰撞的类
    }

    public boolean isLive() {
        return live;
    }

    //Tank类的成员方法可生成对应的get和set方法，那么在其他类中就可以访问了
    public void setLive(boolean live) {
        this.live = live;
    }


    public boolean isGood() {
        return good;
    }

    //检测坦克是否撞墙
    public boolean collidesWithWall(Wall w) {
        if (this.live && this.getRect().intersects(w.getRect())) {
            this.stay(); //如果坦克撞到墙就让他回到上一个位置
            return true;
        }
        return false;
    }

    //检测坦克是否相撞,java.util.List<E>接口或者类的另一种写法，java.awt中也有List，所以要写明确
    public boolean collidesWithTanks(java.util.List<Tank> tanks) {
        for (int i = 0; i < tanks.size(); i++) {
            Tank t = tanks.get(i);
            if (this != t) {
                if (this.live && t.isLive() && this.getRect().intersects(t.getRect())) {
                    this.stay();
                    t.stay();
                    return true;
                }
            }
        }
        return false;
    }

    //超级炮弹：朝8个方向各发一发炮弹
    private void superFire() {
        Direction[] dirs = Direction.values();
        for (int i = 0; i < 8; i++) { //dirs[8]是STOP
            fire(dirs[i]);
        }
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public class BloodBar {
        public void draw(Graphics g) {
            Color c = g.getColor();
            g.setColor(Color.RED);
            g.drawRect(x, y - 10, WIDTH, 10);
            int w = WIDTH * life / LIFE;
            g.fillRect(x, y - 10, w, 10);
            g.setColor(c);
        }
    }

    //吃血块
    public boolean eat(Blood b) {
        if (this.live && b.isLive() && this.getRect().intersects(b.getRect())) {
            b.setLive(false);
            life = LIFE;
            return true;
        }
        return false;
    }
}
