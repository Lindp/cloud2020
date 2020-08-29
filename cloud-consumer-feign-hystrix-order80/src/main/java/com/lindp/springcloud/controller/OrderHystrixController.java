package com.lindp.springcloud.controller;

import com.lindp.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "paymentInfo_global_fallback")
public class OrderHystrixController {
    private final PaymentHystrixService paymentHystrixService;

    public OrderHystrixController(PaymentHystrixService paymentHystrixService) {
        this.paymentHystrixService = paymentHystrixService;
    }

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_Timeout_Handler", commandProperties = {
            // 设置如果当前方法运行超过3秒钟则服务降级，异常也同样会导致降级
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_Timeout(id);
    }

    public String paymentInfo_Timeout_Handler(Integer id) {
        return "我是客户端80，支付微服务超时或是报错，我不和你玩了，古德拜了";
    }

    @GetMapping("/consumer/payment/hystrix/timeout2/{id}")
    @HystrixCommand // 如果没有特殊配置使用类上的默认fallback方法
    public String paymentInfo_Timeout2(@PathVariable("id") Integer id) {
        int a = 10/0;
        return paymentHystrixService.paymentInfo_Timeout(id);
    }

    public String paymentInfo_global_fallback() {
        return "我是global，全局的服务降级方法，古德拜了";
    }
}
