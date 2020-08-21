package com.mashibing.tank.factory;

import com.mashibing.tank.FireStrategy;
import com.mashibing.tank.TankFrame;
import com.mashibing.tank.constant.Dir;
import com.mashibing.tank.constant.Group;
import com.mashibing.tank.util.PropertyMgr;

import java.awt.*;
import java.util.Random;

import static com.mashibing.tank.TankFrame.GAME_HEIGHT;
import static com.mashibing.tank.TankFrame.GAME_WIDTH;

public class RectTank extends BaseTank {

    private Random random = new Random();

    public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        if (group == Group.GOOD){
            try {
                fs = (FireStrategy) Class.forName(PropertyMgr.getString("goodFS")).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                fs = (FireStrategy) Class.forName(PropertyMgr.getString("badFS")).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        if (!living || this.x > GAME_WIDTH || this.y > GAME_HEIGHT) {
            tf.tanks.remove(this);
        }
        Color c = g.getColor();
        g.setColor(group == Group.GOOD? Color.green:Color.blue);
        g.fillRect(x, y, 50,50);
        g.setColor(c);
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
        if (x > GAME_WIDTH - RectTank.WIDTH - 2) {
            x = GAME_WIDTH - RectTank.WIDTH - 2;
        }
        if (y > GAME_HEIGHT - RectTank.HEIGHT - 2) {
            y = GAME_HEIGHT - RectTank.HEIGHT - 2;
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }
}
