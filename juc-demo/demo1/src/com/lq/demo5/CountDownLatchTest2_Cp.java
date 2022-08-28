package com.lq.demo5;

import java.util.concurrent.CountDownLatch;

/**
 * @author qili
 * @create 2022-08-02-21:37
 */
public class CountDownLatchTest2_Cp {
    public static void main(String[] args) {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch endSignal = new CountDownLatch(5);

        TheWorker theWorker = new TheWorker(startSignal, endSignal);

        for (int i = 0; i < 5; i++) {
            new Thread(theWorker, String.valueOf(i)).start();
        }

        // 此时上述五个线程都在阻塞状态，等待startSignal信号
        System.out.println("Workers are still not working.");
        // 释放上述五个线程
        startSignal.countDown();
        try {
            endSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Workers have done their work.");


    }
}

class TheWorker implements Runnable {
    private CountDownLatch startSignal;
    private CountDownLatch endSignal;

    public TheWorker(CountDownLatch startSignal, CountDownLatch endSignal) {
        this.startSignal = startSignal;
        this.endSignal = endSignal;
    }

    @Override
    public void run() {
        try {
            // 1. 等待开始信号
            this.startSignal.await();
            // 2. 工作
            doWork();
            // 3. 释放结束信号
            this.endSignal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void doWork() {
        System.out.println(Thread.currentThread().getName() + " finished working.");
    }
}
