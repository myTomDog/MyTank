package com.mashibing.tank;

import com.mashibing.tank.constant.Group;
import com.mashibing.tank.factory.BaseTank;
import com.mashibing.tank.util.Audio;

public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(BaseTank t) {
        int bX = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = t.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        t.tf.gf.createBullet(bX, bY, t.dir, t.group, t.tf);
        if (t.group == Group.GOOD) new Thread(() -> new Audio("Audio/tank_fire.wav").play()).start();
    }
}
