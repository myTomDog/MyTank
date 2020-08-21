package com.mashibing.tank.factory;

import com.mashibing.tank.TankFrame;
import com.mashibing.tank.util.Audio;
import com.mashibing.tank.util.ResourceMgr;

import java.awt.*;

public class RectExplode extends BaseExplode {
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x;
    private int y;

    private TankFrame tf;
    private int step = 0;

    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Thread(()->new Audio("audio/explode.wav").play()).start();

        tf.explodes.add(this);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillRect(x,y,5*step,5*step);
        g.setColor(c);
        step++;
        if (step >= 15){
            tf.explodes.remove(this);
        }
    }
}
