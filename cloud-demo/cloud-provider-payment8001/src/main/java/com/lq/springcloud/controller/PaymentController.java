package com.lq.springcloud.controller;

import com.lq.springcloud.entity.CommonResult;
import com.lq.springcloud.entity.Payment;
import com.lq.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qili
 * @create 2022-06-21-22:09
 */
@RestController
public class PaymentController {

    @Value("${spring.application.name}")
    private String serviceId;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        for (ServiceInstance instance : instances) {
            System.out.println("服务id: " + instance.getServiceId() + "\t主机：" + instance.getHost()
            + "\t端口：" + instance.getPort() + "\turi：" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @PostMapping("/payment")
    public CommonResult savePayment(@RequestBody Payment payment) {
        return paymentService.savePayment(payment);
    }

    @GetMapping("/payment/{id}")
    public CommonResult findPaymentById(@PathVariable("id") Long id) {
        return paymentService.findPaymentById(id);
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout() {
        System.out.println("paymentFeignTimeOut from port: " + serverPort);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
