package com.lindp.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrimFallbackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "我是feign设置的fallback PaymentHystrimFallbackService + paymentInfo_OK";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "我是feign设置的fallback PaymentHystrimFallbackService + paymentInfo_Timeout";
    }
}
