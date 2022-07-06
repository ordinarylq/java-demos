package com.lq.demo3;

/**
 * @ClassName: ThreadDemo1
 * @Author: LiQi
 * @Date: 2022-07-06 16:04
 * @Version: V1.0
 * @Description:
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + "外层");
                synchronized (o) {
                    System.out.println(Thread.currentThread().getName() + "中层");
                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName() + "内层");

                    }
                }
            }
        }, "aa").start();
    }
}
