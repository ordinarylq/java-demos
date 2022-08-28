package com.lq.demo2;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author qili
 * @create 2022-08-02-20:45
 */
public class ThreadDemo1_Cp {
    public static void main(String[] args) {
//        ArrayList<String> arrayList = new ArrayList<>();
        // 处理集合并发修改异常的三种方式
        // 方式1：使用Vector
//        Vector<String> arrayList = new Vector<>();

        // 方式2：使用Collections.synchronizedList()
//        List<String> arrayList = Collections.synchronizedList(new ArrayList<String>());

        // 方式3：使用JUC的CopyOnWriteList
        CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                arrayList.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(arrayList);
            }, String.valueOf(i)).start();
        }
    }
}
