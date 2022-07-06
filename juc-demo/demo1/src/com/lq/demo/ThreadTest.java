package com.lq.demo;

/**
 * @ClassName: ThreadTest
 * @Author: LiQi
 * @Date: 2022-07-06 10:16
 * @Version: V1.0
 * @Description:
 */
public class ThreadTest {
    public static void main(String[] args) {
        // 1. 创建自定义线程
        Thread thread = new Thread(() -> {
            System.out.println("Custom-ThreadName=" + Thread.currentThread().getName());
            while (true) {

            }
        });

        // 2. 运行自定义线程-> 此时该自定义线程为守护线程
        thread.setDaemon(true);
        thread.start();

        // 3. 主线程结束
        System.out.println("Main-ThreadName=" + Thread.currentThread().getName());
    }
}
