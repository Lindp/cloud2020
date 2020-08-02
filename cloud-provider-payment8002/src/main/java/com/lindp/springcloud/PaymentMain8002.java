package com.lindp.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * payment模块启动类
 *
 * @author lindp
 * @date 2020/5/27
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.lindp.**.mapper")
public class PaymentMain8002 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8002.class, args);
    }
}
