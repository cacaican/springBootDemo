package com.xiaocai.springboot.integration.rabbitmq.producer.service;

import com.xiaocai.springboot.integration.rabbitmq.config.MQProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/17 15:27
 */
@Service
public class TestProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MQProperties mqProperties;

    public void testSendMessage() {
        rabbitTemplate.convertAndSend(mqProperties.getDefaultExchange(),
                mqProperties.getRouteKey(), "发送了一条信息");
    }
}
