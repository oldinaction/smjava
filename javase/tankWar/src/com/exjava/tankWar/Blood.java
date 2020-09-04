package com.exjava.tankWar;

import java.awt.*;

public class Blood {

    private int x, y, w, h;
    private TankClient tc;

    private boolean live = true;

    private int step = 0;

    int[][] pos = {
            {400, 400}, {420, 400}, {440, 400}, {440, 420}, {440, 440}, {420, 440}, {400, 440}, {400, 420}
    };

    public Blood(TankClient tc) {
        this.x = pos[0][0];
        this.y = pos[0][1];
        this.w = h = 10;
        this.tc = tc;
    }

    public void draw(Graphics g) {
        if (!live) return;

        Color c = g.getColor();
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, w, h);
        g.setColor(c);

        move();
    }

    private void move() {
        step++;

        if (step == pos.length) {
            step = 0;
        }

        x = pos[step][0];
        y = pos[step][1];
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, w, h);
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

}
