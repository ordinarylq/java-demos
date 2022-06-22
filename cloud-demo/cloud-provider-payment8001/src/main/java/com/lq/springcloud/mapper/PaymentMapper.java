package com.lq.springcloud.mapper;

import com.lq.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author qili
 * @create 2022-06-21-21:27
 */
@Mapper
public interface PaymentMapper {
    Integer create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
