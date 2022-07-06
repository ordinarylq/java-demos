package com.lq.demo3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ThreadDemo2
 * @Author: LiQi
 * @Date: 2022-07-06 16:07
 * @Version: V1.0
 * @Description:
 */
public class ThreadDemo2 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new Thread(()-> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "外层");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "中层");
                    lock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + "内层");

                    } finally {
                        lock.unlock();
                    }
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        },"aa").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "外层");
            } finally {
                lock.unlock();
            }
        }, "BB").start();
    }
}
