package com.lq.demo5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName: CyclicBarrierTest1
 * @Author: LiQi
 * @Date: 2022-07-07 16:42
 * @Version: V1.0
 * @Description:
 */
class MyWorker implements Runnable {
    private CyclicBarrier cyclicBarrier;

    public MyWorker(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " has done his work.");
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + " after all workers have done their works.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
public class CyclicBarrierTest1 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("All right. Workers Have done their works and we need to pay them.");
        });
        MyWorker myWorker = new MyWorker(cyclicBarrier);

        for (int i = 0; i < 5; i++) {
            new Thread(myWorker, String.valueOf(i)).start();
        }

    }
}
