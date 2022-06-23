package com.lq.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author qili
 * @create 2022-06-23-21:08
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("/payment/consul")
    public String paymentConsul() {
        return "Spring Cloud With consul: " + serverPort + "\t\t\t" + UUID.randomUUID().toString();
    }
}
