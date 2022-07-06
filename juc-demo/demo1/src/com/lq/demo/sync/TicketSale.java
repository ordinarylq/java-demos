package com.lq.demo.sync;

/**
 * @ClassName: TicketSale
 * @Author: LiQi
 * @Date: 2022-07-06 10:33
 * @Version: V1.0
 * @Description:
 */
/*
多线程编程步骤：
1. 创建资源类，设置属性、操作方法
2. 创建多线程，调用资源类的操作方法
 */
public class TicketSale {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        MyRunnable myRunnable = new MyRunnable();
        myRunnable.setTicket(ticket);

        // 2. 创建多线程
        new Thread(myRunnable, "窗口1").start();
        new Thread(myRunnable, "窗口2").start();
        new Thread(myRunnable, "窗口3").start();

        // 3. 主线程结束
        System.out.println(Thread.currentThread().getName() + "主线程结束");
    }
}

// 1. 创建资源类，设置属性、操作方法
class Ticket {
    private int count = 30;

    public synchronized int sale() {
        if(count > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出第" + count-- + "张票，剩余" + count + "张");
        }
        return count;
    }

    public int getCount() {
        return count;
    }
}

class MyRunnable implements Runnable {
    private Ticket ticket;

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int sale = ticket.sale();
            if(sale == 0) {
                break;
            }
        }
    }
}
