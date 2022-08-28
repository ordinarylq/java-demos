package com.lq.demo5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author qili
 * @create 2022-08-02-21:47
 */
public class CyclicBarrierTest_Cp {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> System.out.println("All right. All workers have done" +
                "their work. We need to pay them"));
        AWorker aWorker = new AWorker(cyclicBarrier);

        for (int i = 0; i < 5; i++) {
            new Thread(aWorker, String.valueOf(i)).start();
        }


    }
}

class AWorker implements Runnable {
    private CyclicBarrier cyclicBarrier;

    public AWorker(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " has done his work.");
            this.cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + " after all workers done their work.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
