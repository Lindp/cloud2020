package com.lindp.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 将Ribbon负载均衡规则替换成自定义的
 * 轮询、随机等
 * 主主启动类中加入下面的注解
 * <pre>
 *     @RibbonClient(name = "http://CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class)
 * </pre>
 * <p>
 * ⚠️ Ribbon的rule类不能放在companyScan能扫描的包及子包下，不然会替换全局的负载均衡规则
 *
 * @author lindp
 * @date 2020/7/16
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}
