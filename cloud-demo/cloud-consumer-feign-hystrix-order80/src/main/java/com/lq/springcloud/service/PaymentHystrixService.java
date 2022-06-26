package com.lq.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author qili
 * @create 2022-06-25-20:14
 */
@FeignClient(name = "cloud-payment-hystrix-service", fallback = PaymentFallbackService.class)//
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/{id}")
    String paymentInfo_OK(@PathVariable("id") Long id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Long id);
}
