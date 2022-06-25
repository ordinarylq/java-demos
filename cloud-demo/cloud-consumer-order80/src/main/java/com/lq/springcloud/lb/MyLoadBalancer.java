package com.lq.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qili
 * @create 2022-06-25-11:35
 * 实现RoundRobin
 */
@Component
public class MyLoadBalancer implements LoadBalancer{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public int getAndIncrement() {
        for(;;) {
            int current = this.atomicInteger.get();
            int next = current + 1;
            if(atomicInteger.compareAndSet(current, next)) {
                return next;
            }
        }
    }


    @Override
    public ServiceInstance getInstance(List<ServiceInstance> instanceList) {
        // 1. 根据算法得出要找的Service在list中的index
        int index = getAndIncrement() % instanceList.size();

        // 2. 返回index对应的ServiceInstance
        return instanceList.get(index);
    }
}
