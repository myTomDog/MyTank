package com.mashibing.tank.factory;

import com.mashibing.tank.FireStrategy;
import com.mashibing.tank.TankFrame;
import com.mashibing.tank.constant.Dir;
import com.mashibing.tank.constant.Group;
import com.mashibing.tank.util.PropertyMgr;
import com.mashibing.tank.util.ResourceMgr;

import java.awt.*;

public abstract class BaseTank {
    public static final int SPEED = PropertyMgr.getInt("tankSpeed");
    public int x;
    public int y;
    public Dir dir;
    public TankFrame tf;
    public FireStrategy fs;
    public Group group;
    public boolean living = true;
    public boolean moving = true;
    public Rectangle rect = new Rectangle();
    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();

    public abstract void paint(Graphics g);

    public final void setDir(Dir dir) {
        this.dir = dir;
    }

    public final void fire() {
        fs.fire(this);
    }

    public final void die() {
        this.living = false;
    }

    public final void setMoving(boolean moving) {
        this.moving = moving;
    }

}
