package com.lq.demo4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author qili
 * @create 2022-08-02-21:18
 */
// 使用Callable接口方式创建多线程的步骤
/*
* 1. 实现接口Callable, 重写call()
* 2. 创建实现类对象，将该对象作为参数传入FutureTask构造器中
* 3. 将FutureTask对象传入到Thread构造器中
* 4. 调用线程的start()
* 5. 如果call()设置了返回值，可以通过FutureTask对象调用get()获取值
* 6. 可以通过FutureTask对象调用isDone()方法查询线程是否完成执行call()方法
*
* */

public class ThreadDemo2 {
    public static void main(String[] args) {
        MyCallable myCallable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        new Thread(futureTask, "AA").start();

        while (!futureTask.isDone()) {
            System.out.println(Thread.currentThread().getName() + " is waiting for the thread to invoke call()");
        }

        try {
            System.out.println("返回值：" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " ends.");
    }
}

class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(1L);
        System.out.println(Thread.currentThread().getName() + "正在执行call()方法...");
        return "call~~~~~";
    }
}
