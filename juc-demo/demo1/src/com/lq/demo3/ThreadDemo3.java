package com.lq.demo3;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadDemo3
 * @Author: LiQi
 * @Date: 2022-07-06 16:20
 * @Version: V1.0
 * @Description:
 */
public class ThreadDemo3 {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();

        new Thread(() -> {
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + "获取锁o1, 尝试获取锁o2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + "获取锁o2");
                }
            }
        }, "AA").start();

        new Thread(() -> {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + "获取锁o2, 尝试获取锁01");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "获取锁o1");
                }
            }
        }, "BB").start();
    }
}
