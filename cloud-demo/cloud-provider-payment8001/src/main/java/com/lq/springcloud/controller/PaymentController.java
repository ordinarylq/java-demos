package com.lq.springcloud.controller;

import com.lq.springcloud.entity.CommonResult;
import com.lq.springcloud.entity.Payment;
import com.lq.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qili
 * @create 2022-06-21-22:09
 */
@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public CommonResult savePayment(@RequestBody Payment payment) {
        return paymentService.savePayment(payment);
    }

    @GetMapping("/payment/{id}")
    public CommonResult findPaymentById(@PathVariable("id") Long id) {
        return paymentService.findPaymentById(id);
    }
}
