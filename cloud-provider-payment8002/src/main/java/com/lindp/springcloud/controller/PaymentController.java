package com.lindp.springcloud.controller;


import com.lindp.springcloud.entity.CommonResult;
import com.lindp.springcloud.entity.Payment;
import com.lindp.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lindp
 * @since 2020-05-30
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/create")
    public CommonResult<Object> create(@RequestBody Payment entity) {
        boolean result = paymentService.save(entity);
        if (result) {
            return new CommonResult<>(200, "插入数据库成功，serverPort：" + serverPort, result);
        }
        return new CommonResult<>(444, "插入数据库失败，serverPort：" + serverPort, null);
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getById(id);
        if (payment != null) {
            return new CommonResult<Payment>(200, "查询成功，serverPort：" + serverPort, payment);
        } else {
            return new CommonResult<Payment>(444, "没有对应记录，查询ID：" + id + "，serverPort：" + serverPort, null);
        }
    }

    @GetMapping("/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
