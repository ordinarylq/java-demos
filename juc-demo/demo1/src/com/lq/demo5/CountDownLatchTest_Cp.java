package com.lq.demo5;

import java.util.concurrent.CountDownLatch;

/**
 * @author qili
 * @create 2022-08-02-21:31
 */
public class CountDownLatchTest_Cp {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        MyRunCp myRunCp = new MyRunCp(countDownLatch);
        for (int i = 0; i < 4; i++) {
            new Thread(myRunCp, String.valueOf(i)).start();
        }

        try {
            System.out.println(Thread.currentThread().getName() + " will wait to get the signal.");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " got the signal.");

    }
}

class MyRunCp implements Runnable {
    private CountDownLatch countDownLatch;

    public MyRunCp(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        this.countDownLatch.countDown();
        System.out.println(Thread.currentThread().getName() + " did a countdown op.");
    }
}
