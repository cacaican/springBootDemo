package com.xiaocai.springboot.integration.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 本配置类测试了topic主题发布功能，验证消息发送中的匹配功能，* 只能匹配单层，#能匹配多层
 * 其中coreExchange 是使用api.core.*
 * paymentExchange 使用api.payment.#
 * 交换机与队列关联时候使用的是BindingBuilder.bing(队列quene).to（交换机）。with（匹配路径）
 * 所以再testUser时候能够正常发送和接受
 * 但是再testUser时候，因为交换机与队列bind的是api.core.* 无法多层匹配，所以只能发送，未被消费
 * @author: xiaocai
 * @time: 2022/3/17 16:17
 */
@Configuration
public class TopicConfig {
    @Bean
    public Queue coreQueue() {
        return new Queue("api.core");
    }

    @Bean
    public Queue paymentQueue() {
        return new Queue("api.payment");
    }

    @Bean
    public TopicExchange coreExchange() {
        return new TopicExchange("coreExchange");
    }

    @Bean
    public TopicExchange paymentExchange() {
        return new TopicExchange("paymentExchange");
    }

    @Bean
    public Binding bindingCoreExchange(Queue coreQueue, TopicExchange coreExchange) {
        return BindingBuilder.bind(coreQueue).to(coreExchange).with("api.core.*");
    }

    @Bean
    public Binding bindingPaymentExchange(Queue paymentQueue, TopicExchange paymentExchange) {
        return BindingBuilder.bind(paymentQueue).to(paymentExchange).with("api.payment.#");
    }
}
