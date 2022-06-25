package com.lq.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author qili
 * @create 2022-06-25-11:35
 */
public interface LoadBalancer {
    ServiceInstance getInstance(List<ServiceInstance> instanceList);
}
