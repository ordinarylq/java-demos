package com.lq.demo2;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: ThreadDemo3
 * @Author: LiQi
 * @Date: 2022-07-06 15:14
 * @Version: V1.0
 * @Description:
 */
public class ThreadDemo3 {
    public static void main(String[] args) {
        //HashMap<String, String> map = new HashMap<>();
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 10; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                map.put(key, UUID.randomUUID().toString().substring(0, 3));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
