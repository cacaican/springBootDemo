package com.xiaocai.springboot.integration.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/17 16:05
 */
@Configuration
public class DirectConfig {
    @Bean
    public Queue paymentNotifyQueue() {
        return new Queue("notify.payment");
    }
}
