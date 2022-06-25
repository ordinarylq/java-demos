package com.lq.springcloud.controller;

import com.lq.springcloud.entity.CommonResult;
import com.lq.springcloud.entity.Payment;
import com.lq.springcloud.lb.MyLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author qili
 * @create 2022-06-21-22:57
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    public static final String SERVICE_ID = "CLOUD-PAYMENT-SERVICE";

    @Autowired
    private MyLoadBalancer myLoadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/payment")
    public CommonResult savePayment(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment", payment, CommonResult.class);
    }

    @GetMapping("/payment/{id}")
    public CommonResult findPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, CommonResult.class, id);
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        // 1. 根据serviceID获取所有服务
        List<ServiceInstance> instances = discoveryClient.getInstances(SERVICE_ID);

        // 2. 判断是否有服务实例
        if(instances == null || instances.size() <= 0) {
            return null;
        }

        // 2. 根据myLoadBalancer获取指定服务实例
        ServiceInstance instance = myLoadBalancer.getInstance(instances);

        // 3. 获取指定服务的uri
        URI uri = instance.getUri();

        // 4. 使用RestTemplate进行RPC
        return restTemplate.getForObject(uri + "payment/lb", String.class);
    }
}
