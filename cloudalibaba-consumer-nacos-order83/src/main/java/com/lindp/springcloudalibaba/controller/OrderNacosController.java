package com.lindp.springcloudalibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderNacosController {
    @Resource
    private RestTemplate restTemplate;

    public static final String SERVER_URL = "http://nacos-payment-provider";

    @GetMapping("/consumer/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(SERVER_URL + "/payment/nacos/" + id, String.class);
    }
}
