package com.lq.demo5;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SemaphoreTest
 * @Author: LiQi
 * @Date: 2022-07-08 13:25
 * @Version: V1.0
 * @Description:
 */
public class SemaphoreTest {
    /**
     * 六个线程抢占三个信号量
     * @author LiQi
     * @param args
     */
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    // 1. 获取信号
                    semaphore.acquire();
                    // 2. 线程处理工作
                    System.out.println(Thread.currentThread().getName() + "  获得了信号");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    // 3. 工作处理完成
                    System.out.println(Thread.currentThread().getName() + "--工作结束");
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
