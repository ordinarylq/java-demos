package com.lq.demo5;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: CountDownLatchTest
 * @Author: LiQi
 * @Date: 2022-07-07 16:14
 * @Version: V1.0
 * @Description:
 */
class MyRunnable implements Runnable {
    private CountDownLatch countDownLatch;

    public MyRunnable(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        this.countDownLatch.countDown();
        System.out.println(Thread.currentThread().getName() + " down -1 op");
    }
}

public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        MyRunnable myRunnable = new MyRunnable(countDownLatch);

        for (int i = 0; i < 5; i++) {
            new Thread(myRunnable, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(Thread.currentThread().getName() + " is over!");
    }
}
