package com.lq.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author qili
 * @create 2022-06-25-21:23
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Long id) {
        return "paymentInfo_OK服务调用失败，提示来自：cloud-consumer-feign-order80";
    }

    @Override
    public String paymentInfo_TimeOut(Long id) {
        return "paymentInfo_TimeOut服务调用失败，提示来自：cloud-consumer-feign-order80";
    }
}
