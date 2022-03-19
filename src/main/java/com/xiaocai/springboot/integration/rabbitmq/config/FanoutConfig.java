package com.xiaocai.springboot.integration.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @description:
 * FanoutExchange交换机是转发消息到所有绑定队列（广播模式，和routingKey没有关系）。
 *
 * 添加一个配置类（消费者）
 * 配置一个routingKey为api.report.payment的消息队列并绑定在reportExchange交换机上
 * 配置一个routingKey为api.report.refund的消息队列并绑定在reportExchange交换机上
 * @author: xiaocai
 * @time: 2022/3/18 16:05
 */
@Configuration
public class FanoutConfig {
    @Bean
    public Queue reportPaymentQueue() {
        return new Queue("api.report.payment");
    }

    @Bean
    public Queue reportRefundQueue() {
        return new Queue("api.report.refund");
    }

    @Bean
    public FanoutExchange reportExchange() {
        return new FanoutExchange("reportExchange");
    }

    @Bean
    public Binding bindingReportPaymentExchange(Queue reportPaymentQueue, FanoutExchange reportExchange) {
        return BindingBuilder.bind(reportPaymentQueue).to(reportExchange);
    }

    @Bean
    public Binding bindingReportRefundExchange(Queue reportRefundQueue, FanoutExchange reportExchange) {
        return BindingBuilder.bind(reportRefundQueue).to(reportExchange);
    }
}