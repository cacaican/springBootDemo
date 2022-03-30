package com.xiaocai.springboot.integration.rabbitmq.producer.component;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @description: 手动发送rabbitmq
 * @author: xiaocai
 * @time: 2022/3/28 19:58
 */
public class manualSendDemo {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 RabbitMQ 的主机名
        factory.setHost("localhost");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 指定一个队列,不存在的话自动创建
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.txSelect();

        // 发送消息
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        channel.txCommit();
        System.out.println(" [x] Sent '" + message + "'");

        // 关闭频道和连接
        channel.close();
        connection.close();
    }
}
