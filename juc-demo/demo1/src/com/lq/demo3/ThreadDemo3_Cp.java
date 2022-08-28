package com.lq.demo3;

import java.util.concurrent.TimeUnit;

/**
 * @author qili
 * @create 2022-08-02-21:09
 */
public class ThreadDemo3_Cp {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();

        new Thread(() -> {
            synchronized (o1) {
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "获取o1, 等待获取o2...");
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + "获取o2!");
                }
            }
        }, "AA").start();

        new Thread(() -> {
            synchronized (o2) {
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "获取o2, 等待获取o1...");
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "获取o1!");
                }
            }
        }, "BB").start();
    }
}
