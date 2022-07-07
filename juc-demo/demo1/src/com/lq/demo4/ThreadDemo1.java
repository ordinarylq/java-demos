package com.lq.demo4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadDemo1
 * @Author: LiQi
 * @Date: 2022-07-07 15:51
 * @Version: V1.0
 * @Description:
 */
class MyCall implements Callable<String> {
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName() + "-Call()...");
        return "MyCall";
    }
}

public class ThreadDemo1 {

    public static void main(String[] args) {
        MyCall myCall = new MyCall();
        FutureTask<String> futureTask = new FutureTask<>(myCall);

        new Thread(futureTask, "AA").start();

        while (!futureTask.isDone()) {
            System.out.println(Thread.currentThread().getName() + " is waiting...");
        }

        try {
            System.out.println("Result = " + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " is over.");


    }
}
