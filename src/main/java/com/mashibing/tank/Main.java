package com.mashibing.tank;

import com.mashibing.tank.constant.Dir;
import com.mashibing.tank.constant.Group;
import com.mashibing.tank.util.Audio;
import com.mashibing.tank.util.PropertyMgr;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        int initTankCount = PropertyMgr.getInt("initTankCount");

        // 初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tf.tanks.add(tf.gf.createTank(50 + i * 100, 100, Dir.DOWN, Group.BAD, tf));
        }

        new Thread(()->new Audio("Audio/war1.wav").loop()).start();

        while (true) {
            Thread.sleep(25);
            tf.repaint();
        }
    }
}
