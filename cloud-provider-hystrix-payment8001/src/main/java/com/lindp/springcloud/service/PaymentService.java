package com.lindp.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id) {
        return String.format("线程池：%s；方法：paymentInfo_OK；id：%s；", Thread.currentThread().getName(), id);
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_Timeout_Handler", commandProperties = {
            // 设置如果当前方法运行超过3秒钟则服务降级，异常也同样会导致降级
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_Timeout(Integer id) {
        // int a = 10 / 0 /// 同样会触发服务降级
        int num = 5;
        try {
            TimeUnit.SECONDS.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.format("线程池：%s；方法：paymentInfo_Timeout；id：%s；耗时%s秒", Thread.currentThread().getName(), id, num);
    }

    public String paymentInfo_Timeout_Handler(Integer id) {
        return String.format("线程池：%s；方法：paymentInfo_Timeout_Handler；id：%s；触发服务降级，暂时不可用", Thread.currentThread().getName(), id);
    }

    /////// 断路器部分
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 失败率达到多少跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "调用成功，流水号" + serialNumber;
    }

    public String paymentCircuitBreakerFallback(Integer id) {
        return "id 不能负数，请稍后再试，😭 id：" + id;
    }
}
