package com.lq.demo5;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author qili
 * @create 2022-08-02-21:55
 */
public class SemaphoreTest_Cp {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    // 1.获取信号
                    semaphore.acquire();
                    // 2. 处理工作
                    System.out.println(Thread.currentThread().getName() + " got the semaphore.");
                    TimeUnit.SECONDS.sleep(1L);
                    // 3. 工作处理完成
                    System.out.println(Thread.currentThread().getName() + " finished its work.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 4. 释放信号
                    semaphore.release();
                }

            }, String.valueOf(i)).start();
        }
    }
}
