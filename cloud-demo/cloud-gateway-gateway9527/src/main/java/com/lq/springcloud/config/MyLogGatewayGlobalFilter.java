package com.lq.springcloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * @author qili
 * @create 2022-08-28-14:28
 */
@Slf4j
public class MyLogGatewayGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("time:" + LocalDateTime.now() + "\t executed MyLogGatewayGlobalFilter");
        // 判断请求是否携带了uname请求参数
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if(uname == null) {
            // 不存在则直接返回400
            log.info("uname为null, 无法登录！");
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            return exchange.getResponse().setComplete();
        } else {
            // 存在则继续filterChain
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        // 数字越小优先级越高
        return 0;
    }
}
