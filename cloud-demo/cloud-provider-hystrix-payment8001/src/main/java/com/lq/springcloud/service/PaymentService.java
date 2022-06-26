package com.lq.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

/**
 * @author qili
 * @create 2022-06-25-20:01
 */
@Service
public class PaymentService {
    public String paymentInfo_OK(Long id) {
        return "线程池：" + Thread.currentThread().getName() + "\tpaymentInfo_OK, id=" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_timeout(Long id) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //int i = 10 / 0;
        return "线程池：" + Thread.currentThread().getName() + "\tpaymentInfo_timeout, id=" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            // 是否启用断路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            // 在时间窗口中，断路器熔断的最小请求数，超过该值，则断路器从close转为open
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 断路器open之后的休眠时间窗，该窗口结束后，会将断路器设置为half open状态，当请求来时尝试处理，如果
            // 依然失败则将断路器设置为open，如果成功则设置为close
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            // 在滚动时间窗中，请求数量超过circuitBreaker.requestVolumeThreshold的情况下，如果错误请求数百分比超过该值，
            // 则设置断路器为open,否则设置为close
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String paymentCircuitBreaker(Long id) {
        if(id < 0) {
            throw new RuntimeException("------------id不能为负数------------");
        }
        String serialNum = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t调用成功，序列号=" + serialNum;
    }

    public String paymentInfo_TimeOutHandler(Long id) {
        return "调用服务超时或出现异常，线程名：" + Thread.currentThread().getName();
    }
    //---------------------服务熔断---------------------

    public String paymentCircuitBreaker_fallback(Long id) {
        return "id不能为负数，请稍后再试~~~~~~~id=" + id;
    }
}
