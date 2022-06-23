package com.lq.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author qili
 * @create 2022-06-23-22:21
 */
@RestController
public class OrderConsulController {
    public static final String SERVICE_ID = "http://cloud-payment-service";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String paymentInfo() {
        return restTemplate.getForObject(SERVICE_ID + "/payment/consul", String.class);
    }
}
