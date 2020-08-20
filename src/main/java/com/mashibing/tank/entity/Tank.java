package com.mashibing.tank.entity;

import com.mashibing.tank.TankFrame;
import com.mashibing.tank.constant.Dir;
import com.mashibing.tank.constant.Group;
import com.mashibing.tank.util.Audio;
import com.mashibing.tank.util.PropertyMgr;
import com.mashibing.tank.util.ResourceMgr;

import java.awt.*;
import java.util.Random;

import static com.mashibing.tank.TankFrame.GAME_HEIGHT;
import static com.mashibing.tank.TankFrame.GAME_WIDTH;

public class Tank {

    public static final int SPEED = PropertyMgr.getInt("tankSpeed");
    public int x;
    public int y;
    public Dir dir;
    public TankFrame tf;
    public Group group;
    public boolean living = true;
    public boolean moving = true;
    public Rectangle rect = new Rectangle();
    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();

    public final void setDir(Dir dir) {
        this.dir = dir;
    }

    public final void fire() {
        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        new Bullet(bX, bY, this.dir, this.group, this.tf);
        if (this.group == Group.GOOD) new Thread(() -> new Audio("Audio/tank_fire.wav").play()).start();
    }

    public final void die() {
        this.living = false;
    }

    public final void setMoving(boolean moving) {
        this.moving = moving;
    }

    private Random random = new Random();

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics g) {
        if (!living || this.x > GAME_WIDTH || this.y > GAME_HEIGHT) {
            tf.tanks.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        if (!moving) return;
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
        }

        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }

        boundsCheck();

        // update rect
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (x < 2) {
            x = 2;
        }
        if (y < 28) {
            y = 28;
        }
        if (x > GAME_WIDTH - Tank.WIDTH - 2) {
            x = GAME_WIDTH - Tank.WIDTH - 2;
        }
        if (y > GAME_HEIGHT - Tank.HEIGHT - 2) {
            y = GAME_HEIGHT - Tank.HEIGHT - 2;
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

}
