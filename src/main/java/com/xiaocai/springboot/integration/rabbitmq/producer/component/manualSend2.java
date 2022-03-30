package com.xiaocai.springboot.integration.rabbitmq.producer.component;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @description: 手动发送rabbitmq，使用持久化方式确认消息已经发送了
 * 并且测试报错情况下，是否能否正常发送；发送失败，事务回滚了
 * @author: xiaocai
 * @time: 2022/3/28 19:58
 */
public class manualSend2 {

    private final static String EXCHANGE_NAME = "xiaocai-confirm-exchange";

    private final static String QUEUE_NAME = "xc2Queue";

    public static void main(String[] args) throws IOException, TimeoutException {


            //创建连接工厂
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("127.0.0.1");

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
            //创建消息
            String message = "xiaocai是天才哈哈哈";
            channel.txSelect();
        try {

            //简单发布
            channel.basicPublish(EXCHANGE_NAME,QUEUE_NAME,true,null,message.getBytes(StandardCharsets.UTF_8));

            System.out.println(" [x] Sent '" + message + "'");
            //            int i = 1/0;
            channel.txCommit();

            // 关闭频道和连接

        }catch (Exception e){
            e.printStackTrace();
            channel.txRollback();

        } finally {
            channel.close();
            connection.close();
        }




    }
}
