package com.lq.springcloud.service;

import com.lq.springcloud.entity.CommonResult;
import com.lq.springcloud.entity.Payment;

/**
 * @author qili
 * @create 2022-06-21-22:01
 */
public interface PaymentService {
    CommonResult<Integer> savePayment(Payment payment);

    CommonResult<Payment> findPaymentById(Long id);
}
