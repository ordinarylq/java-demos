package com.lq.springcloud.service.impl;

import com.lq.springcloud.entity.CommonResult;
import com.lq.springcloud.entity.Payment;
import com.lq.springcloud.mapper.PaymentMapper;
import com.lq.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author qili
 * @create 2022-06-21-22:03
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${server.port}")
    private Integer serverPort;

    @Autowired
    private PaymentMapper paymentMapper;

    public CommonResult<Integer> savePayment(Payment payment) {
        Integer result = paymentMapper.create(payment);

        CommonResult<Integer> commonResult;
        if(result > 0) {
            commonResult = new CommonResult<>(200, "插入数据成功,端口：" + serverPort, result);
        } else {
            commonResult = new CommonResult<>(444, "插入数据失败", null);
        }

        return commonResult;
    }

    public CommonResult<Payment> findPaymentById(Long id) {
        Payment payment = paymentMapper.getPaymentById(id);

        CommonResult<Payment> commonResult;
        if(payment != null) {
            commonResult = new CommonResult<>(200, "查询数据成功,端口：" + serverPort, payment);
        } else {
            commonResult = new CommonResult<>(444, "查询数据失败，查询id:" + id, null);
        }

        return commonResult;
    }
}
