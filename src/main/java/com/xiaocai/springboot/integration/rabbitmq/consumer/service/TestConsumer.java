package com.xiaocai.springboot.integration.rabbitmq.consumer.service;

import com.xiaocai.springboot.integration.utils.RabbitMQUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import com.rabbitmq.client.Channel;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/17 15:28
 */
@Service
public class TestConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestConsumer.class);

    @RabbitListener(queues = "${mq.queue}")
    public void receive(String payload, Channel channel,
                        @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        LOGGER.info("消费内容为：{}", payload);
        RabbitMQUtils.askMessage(channel, tag, LOGGER);
    }
}
