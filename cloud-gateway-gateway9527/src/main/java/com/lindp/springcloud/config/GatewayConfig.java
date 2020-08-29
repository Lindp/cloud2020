package com.lindp.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("baidu", r ->
                        r.path("/guonei").uri("http://news.baidu.com/guonei"))
                .route("baidu_game", r ->
                        r.path("/game").uri("http://news.baidu.com/game"))
                .build();
    }
}
