package com.xiaocai.springboot.integration.rabbitmq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: 本配置类读取了aplication.yml中的配置文件，使用了默认的配置：即默认交换机amqpExchange
 *   defaultExchange: amqpExchange # 默认交换器
 *   queue: queue # 队列名
 *   routeKey: queue_key # 路由key
 * @author: xiaocai
 * @time: 2022/3/17 15:20
 */
@Component
@ConfigurationProperties(prefix = "mq")
public class MQProperties {
    private String defaultExchange;
    private String routeKey;
    private String queue;

    public String getDefaultExchange() {
        return defaultExchange;
    }

    public void setDefaultExchange(String defaultExchange) {
        this.defaultExchange = defaultExchange;
    }

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }
}