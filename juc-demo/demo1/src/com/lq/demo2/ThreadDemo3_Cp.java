package com.lq.demo2;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qili
 * @create 2022-08-02-20:57
 */
public class ThreadDemo3_Cp {
    public static void main(String[] args) {
        //HashMap<String, String> hashMap = new HashMap<>();
        // 使用ConcurrentHashMap
        ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                String key = UUID.randomUUID().toString().substring(0, 5);
                String value = UUID.randomUUID().toString().substring(0, 5);
                hashMap.put(key, value);
                System.out.println(hashMap);
            }, String.valueOf(i)).start();
        }
    }
}
