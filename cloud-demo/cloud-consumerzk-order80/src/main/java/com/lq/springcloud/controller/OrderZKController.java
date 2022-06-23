package com.lq.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author qili
 * @create 2022-06-23-20:29
 */
@RestController
public class OrderZKController {
    public static final String SERVICE_ID = "http://cloud-payment-service";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String paymentInfo() {
        return restTemplate.getForObject(SERVICE_ID + "/payment/zk", String.class);
    }
}
