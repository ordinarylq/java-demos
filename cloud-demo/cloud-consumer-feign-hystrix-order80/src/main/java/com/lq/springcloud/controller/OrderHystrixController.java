package com.lq.springcloud.controller;

import com.lq.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qili
 * @create 2022-06-25-20:20
 */
@RestController
@RequestMapping("/consumer")
//@DefaultProperties(defaultFallback = "payment_global_fallback")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/hystrix/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id) {
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
//    })
    //@HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Long id) {
        //int i = 10 / 0;
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    public String paymentTimeOutFallbackMethod(Long id) {
        return "消费端80, “服务提供方繁忙”或“消费端出现异常” ~~~~~~~~~";
    }

    public String payment_global_fallback() {
        return "global fallback~~~“服务提供方繁忙”或“消费端出现异常”";
    }
}
