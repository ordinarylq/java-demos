package com.lq.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qili
 * @create 2022-08-28-9:43
 */
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator01(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_comic", r ->
                r.path("/comic")
                        .uri("http://tv.sohu.com/comic")).build();
        return routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator02(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_drama", r -> r.path("/drama").uri("http://tv.sohu.com/drama")).build();
        return routes.build();
    }

    @Bean
    public MyLogGatewayGlobalFilter myLogGatewayGlobalFilter() {
        return new MyLogGatewayGlobalFilter();
    }
}
