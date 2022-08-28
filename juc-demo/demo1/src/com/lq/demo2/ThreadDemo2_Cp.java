package com.lq.demo2;

import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author qili
 * @create 2022-08-02-20:55
 */
public class ThreadDemo2_Cp {
    public static void main(String[] args) {
        //HashSet<String> hashSet = new HashSet<>();
        // 使用JUC的CopyOnWriteArraySet
        CopyOnWriteArraySet<String> hashSet = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                hashSet.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(hashSet);
            }, String.valueOf(i)).start();
        }
    }
}
