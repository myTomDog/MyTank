package com.mashibing.tank;

import com.mashibing.tank.constant.Group;
import com.mashibing.tank.util.Audio;

public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        int bX = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = t.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        new Bullet(bX, bY, t.dir, t.group, t.gm);
        if (t.group == Group.GOOD) new Thread(() -> new Audio("Audio/tank_fire.wav").play()).start();
    }
}
