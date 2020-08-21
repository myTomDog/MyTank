package com.mashibing.tank;

import com.mashibing.tank.util.Audio;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        // 单独线程循环播放背景音乐
        new Thread(() -> new Audio("Audio/war1.wav").loop()).start();

        // 定时刷新页面
        while (true) {
            Thread.sleep(25);
            tf.repaint();
        }
    }

}
