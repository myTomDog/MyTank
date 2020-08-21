package com.mashibing.tank;

import com.mashibing.tank.constant.Dir;
import com.mashibing.tank.constant.Group;
import com.mashibing.tank.util.PropertyMgr;
import com.mashibing.tank.util.ResourceMgr;

import java.awt.*;
import java.util.Random;

import static com.mashibing.tank.TankFrame.GAME_HEIGHT;
import static com.mashibing.tank.TankFrame.GAME_WIDTH;

public class Tank {

    public int x;
    public int y;
    public Group group;
    public Dir dir;
    public FireStrategy fs;
    public GameModel gm;
    public Rectangle rect = new Rectangle();

    private boolean living = true;
    private boolean moving = true;
    private Random random = new Random();

    public static final int SPEED = PropertyMgr.getInt("tankSpeed");
    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();

    public Tank(int x, int y, Dir dir, Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gm = gm;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        if (group == Group.GOOD) {
            try {
                fs = (FireStrategy) Class.forName(PropertyMgr.getString("goodFS")).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                fs = (FireStrategy) Class.forName(PropertyMgr.getString("badFS")).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g) {
        if (!living || this.x > GAME_WIDTH || this.y > GAME_HEIGHT) {
            gm.tanks.remove(this);
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

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        fs.fire(this);
    }

    public void die() {
        this.living = false;
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
