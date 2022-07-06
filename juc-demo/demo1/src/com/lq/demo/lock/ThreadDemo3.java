package com.lq.demo.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ThreadDemo3
 * @Author: LiQi
 * @Date: 2022-07-06 14:05
 * @Version: V1.0
 * @Description:
 */
class ShareResource {
    // 标志位 1-AA线程 2-BB线程 3-CC线程
    private int flag = 1;

    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(int loopNo) throws InterruptedException {
        // 加锁
        lock.lock();
        try {
            // 2.1 循环判断
            while (flag != 1) {
                c1.await();
            }

            // 2.2 操作
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i + "::" + loopNo);
            }

            // 2.3 通知
            // 修改标志位
            flag = 2;
            c2.signal();

        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public void print10(int loopNo) throws InterruptedException {
        // 加锁
        lock.lock();
        try {
            // 2.1 循环判断
            while (flag != 2) {
                c2.await();
            }

            // 2.2 操作
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i + "::" + loopNo);
            }

            // 2.3 通知
            flag = 3;
            c3.signal();
        } finally {
            // 解锁
            lock.unlock();
        }
    }

    public void print15(int loopNo) throws InterruptedException {
        // 加锁
        lock.lock();
        try {
            // 2.1 循环判断
            while (flag != 3) {
                c3.await();
            }

            // 2.2 操作
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i + "::" + loopNo);
            }

            // 2.3 通知
            flag = 1;
            c1.signal();
        } finally {
            // 解锁
            lock.unlock();
        }
    }
}

public class ThreadDemo3 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        // 3. 创建多线程，调用资源操作方法
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();

    }
}
