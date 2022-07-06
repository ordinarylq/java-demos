package com.lq.demo2;

import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName: ThreadDemo2
 * @Author: LiQi
 * @Date: 2022-07-06 15:11
 * @Version: V1.0
 * @Description:
 */
public class ThreadDemo2 {
    public static void main(String[] args) {
        //HashSet<String> set = new HashSet<>();
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 3));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
