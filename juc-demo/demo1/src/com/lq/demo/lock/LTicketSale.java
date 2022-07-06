package com.lq.demo.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: LTicketSale
 * @Author: LiQi
 * @Date: 2022-07-06 11:10
 * @Version: V1.0
 * @Description:
 */
public class LTicketSale {
    public static void main(String[] args) {
        LTicket ticket = new LTicket();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "窗口1").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "窗口2").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "窗口3").start();
    }
}

class LTicket {
    private int number = 30;
    private final ReentrantLock lock = new ReentrantLock();

    public void sale() {
        // 1. 上锁
        lock.lock();

        try {
            // 2. 操作
            if(number > 0) {
                System.out.println(Thread.currentThread().getName() + "售出第" + number-- + "张票，剩余" + number + "张");
            }
        } finally {
            // 3. 解锁
            lock.unlock();
        }
    }
}
