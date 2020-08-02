package com.lindp.springcloud.service.impl;

import com.lindp.springcloud.entity.Payment;
import com.lindp.springcloud.mapper.PaymentMapper;
import com.lindp.springcloud.service.PaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lindp
 * @since 2020-05-30
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

}
