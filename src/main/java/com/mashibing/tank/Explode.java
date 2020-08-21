package com.mashibing.tank;

import com.mashibing.tank.factory.BaseExplode;
import com.mashibing.tank.util.Audio;
import com.mashibing.tank.util.ResourceMgr;

import java.awt.*;

public class Explode extends BaseExplode {
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x;
    private int y;

    private TankFrame tf;
    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Thread(()->new Audio("audio/explode.wav").play()).start();

        tf.explodes.add(this);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length){
            tf.explodes.remove(this);
        }
    }
}
