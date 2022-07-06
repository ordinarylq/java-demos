package com.lq.demo2;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName: ThreadDemo1
 * @Author: LiQi
 * @Date: 2022-07-06 14:37
 * @Version: V1.0
 * @Description:
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        //ArrayList<String> list = new ArrayList<>();
        // 对ArrayList迭代时进行修改就会报ConcurrentModificationException

        // 1. 使用Vector
        //Vector<String> list = new Vector<>();

        // 2. 使用Collections的方法synchronizedList(List<T> list)
        //List<String> list = Collections.synchronizedList(new ArrayList<String>());

        // 3. 使用CopyOnWriteArrayList
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 3));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
