package com.mashibing.tank.entity;

import com.mashibing.tank.TankFrame;
import com.mashibing.tank.constant.Dir;
import com.mashibing.tank.constant.Group;
import com.mashibing.tank.util.PropertyMgr;
import com.mashibing.tank.util.ResourceMgr;

import java.awt.*;

public class Bullet {
    public static final int SPEED = PropertyMgr.getInt("bulletSpeed");
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

    private Rectangle rect = new Rectangle();
    private int x;
    private int y;
    private Dir dir;
    private boolean living = true;
    private Group group;
    private TankFrame tf;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        tf.bullets.add(this);
    }

    public void paint(Graphics g) {

        if (!living || this.x > TankFrame.GAME_WIDTH || this.y > TankFrame.GAME_HEIGHT) {
            tf.bullets.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
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
        }

        // update rect
        rect.x = this.x;
        rect.y = this.y;

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }

    /**
     * 碰撞检测
     *
     * @param tank
     */
    public void collideWith(Tank tank) {
        // 子弹坦克属于一方则不伤害
        if (this.group == tank.group) return;

        // 判断是否相交
        if (rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            int eX = tank.x + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.y + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            new Explode(eX, eY, tf);
        }
    }

    private void die() {
        this.living = false;
    }
}
