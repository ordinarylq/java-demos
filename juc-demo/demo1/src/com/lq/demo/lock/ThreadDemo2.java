package com.lq.demo.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ThreadDemo2
 * @Author: LiQi
 * @Date: 2022-07-06 13:37
 * @Version: V1.0
 * @Description:
 */
// 1. 创建资源类，设置属性、方法
class Share {
    private int number = 0;
    private final Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void incr() throws InterruptedException {
        // 加锁
        lock.lock();
        try {
            // 2.1 循环判断
            while (number != 0) {
                condition.await();
            }

            // 2.2 操作
            number++;
            System.out.println(Thread.currentThread().getName() + "值为" + number);

            // 2.3 通知
            condition.signalAll();

        } finally {
            // 解锁
            lock.unlock();
        }
    }

    public void decr() throws InterruptedException {
        // 1. 加锁
        lock.lock();
        try {
            // 2.1 循环判断
            while (number != 1) {
                condition.await();
            }

            // 2.2 操作
            number--;
            System.out.println(Thread.currentThread().getName() + "值为" + number);

            // 2.3 通知
            condition.signalAll();
        } finally {
            // 2. 解锁
            lock.unlock();
        }
    }
}
public class ThreadDemo2 {

    public static void main(String[] args) {
        Share share = new Share();

        // 3. 创建多线程，调用资源操作方法
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
