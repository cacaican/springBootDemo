package com.xiaocai.springboot.integration.rabbitmq.producer.component;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description: 手动接受
 * @author: xiaocai
 * @time: 2022/3/28 19:58
 */
public class manualReceive2 {

    private final static String EXCHANGE_NAME = "xiaocai-confirm-exchange";

    private final static String QUEUE_NAME = "xcQueue2";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPassword("guest");
        connectionFactory.setUsername("guest");

        //创建连接
        Connection connection = connectionFactory.newConnection();



        //船舰通道
        Channel channel = connection.createChannel();
        //指定交换机,没有就创建,类型有 DIRECT("direct"), FANOUT("fanout"), TOPIC("topic"), HEADERS("headers");
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        //指定队列，不存在的话自动创建
        /*声明一个队列
        参数：
        queue – 队列的名称
        持久 - 如果我们声明一个持久队列，则为真（该队列将在服务器重新启动后继续存在）
        独占 - 如果我们声明一个独占队列，则为真（仅限于此连接）
        autoDelete – 如果我们声明一个自动删除队列，则为 true（服务器将在不再使用时将其删除）
        arguments – 队列的其他属性（构造参数）
        返回值：
        声明队列已成功声明的声明确认方法*/
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"xiaocai");

        channel.basicConsume(QUEUE_NAME,false,new XiaocaiConsumer(channel));
        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);
        // 消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息


        // 关闭频道和连接
       /* channel.close();
        connection.close();*/

        /*此时运行代码，因为队列没有绑定到交换机，消息肯定没地方存储，但是程序却并未出错，也就是消息丢失了但是我们却并不知晓。

        RabblitMQ针对这个问题，提供了两种解决方案：

        通过事务机制实现
        通过发送方确认（publisher confirm）机制实现*/

    }
}

