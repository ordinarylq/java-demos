package com.lq.demo5;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: CountDownLatchTest2
 * @Author: LiQi
 * @Date: 2022-07-07 16:18
 * @Version: V1.0
 * @Description:
 */
class Worker implements Runnable {
    private CountDownLatch startSignal;
    private CountDownLatch doneSignal;

    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        try {
            // 等待开始信号
            startSignal.await();
            // 开始工作
            doWork();
            // 结束
            doneSignal.countDown();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void doWork() {
        System.out.println(Thread.currentThread().getName() + " has done working.");
    }
}

public class CountDownLatchTest2 {
    public static void main(String[] args) {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(5);

        Worker worker = new Worker(startSignal, doneSignal);

        for (int i = 0; i < 5; i++) {
            new Thread(worker, String.valueOf(i)).start();
        }
        // 此时这5个线程都处于阻塞状态

        // 释放上面5个线程
        System.out.println("Workers are still not working.");
        startSignal.countDown();
        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Workers have done their works.");
    }
}
