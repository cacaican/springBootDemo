package com.xiaocai.springboot.integration.rabbitmq.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/17 16:09
 */
@Component
public class PaymentNotifySender {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentNotifySender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sender(String msg) {
        rabbitTemplate.convertAndSend("notify.payment", msg);

        // 生产者发送消息后，如果发送成功，则打印“消息发送成功”的日志信息
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                LOGGER.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
            }
        });

     /*   // 生产者发送消息后，若发送失败，则输出“消息发送失败”的日志信息
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                LOGGER.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);

            }
        });*/
    }

}



