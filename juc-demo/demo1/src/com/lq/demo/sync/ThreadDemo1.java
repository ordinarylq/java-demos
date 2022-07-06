package com.lq.demo.sync;

/**
 * @ClassName: ThreadDemo1
 * @Author: LiQi
 * @Date: 2022-07-06 13:09
 * @Version: V1.0
 * @Description:
 */
/*
操作：两个线程对一个变量Num操作，初始值为0，一个线程对该变量+1(num=0时)， 另一个线程对该变量-1(num=1时)
 */
// 1. 创建资源类，设置属性、方法
class Share {
    private int number = 0;

    // +1
    public synchronized void incr() throws InterruptedException {
        // 2.1 判断
        while(number != 0) {
            this.wait();
        }

        // 2.2 操作
        number++;
        System.out.println(Thread.currentThread().getName() + "当前number=" + number);

        // 2.3 通知
        this.notifyAll();
    }

    // -1
    public synchronized void decr() throws InterruptedException {
        // 2.1 判断
        while(number != 1) {
            this.wait();
        }

        // 2.2 操作
        number--;
        System.out.println(Thread.currentThread().getName() + "当前number=" + number);

        // 2.3 通知
        this.notifyAll();
    }
}

public class ThreadDemo1 {
    public static void main(String[] args) {
        Share share = new Share();

        //3. 创建多线程，操作资源
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }
}

