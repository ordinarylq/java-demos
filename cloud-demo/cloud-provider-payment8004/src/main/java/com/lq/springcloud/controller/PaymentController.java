package com.lq.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author qili
 * @create 2022-06-23-20:04
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("/payment/zk")
    public String paymentZk() {
        return "Spring Cloud With ZooKeeper: " + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
