package com.xiaocai.springboot.integration.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/14 16:55
 */
@Configuration
@ComponentScan
@EnableAsync
@AutoConfigureOrder(0)
public class RedisSessionConfig implements AsyncConfigurer {

    @Value("${redis.session.mode}")
    private String session_mode;
    @Value("${redis.session.clusterNodes}")
    private String session_clusterNodes;
    @Value("${redis.session.master}")
    private String session_master;
    @Value("${redis.session.sentinelHostAndPorts}")
    private String session_sentinelHostAndPorts;
    @Value("${redis.session.host}")
    private String session_host;
    @Value("${redis.session.port}")
    private int session_port;
    @Value("${redis.session.database}")
    private int session_database;
    @Value("${redis.session.password}")
    private String session_password;
    @Value("${redis.session.usePool}")
    private boolean session_usePool;
    @Value("${redis.session.maxTotal}")
    private int maxTotal;
    @Value("${redis.session.maxIdle}")
    private int maxIdle;
    @Value("${redis.session.minIdle:5}")
    private int minIdle;
    @Value("${redis.session.maxWaitMillis}")
    private int maxWaitMillis;
    @Value("${redis.session.testOnBorrow:true}")
    private boolean testOnBorrow;
    @Value("${unic.defaultExecutor.pool-size}")
    private String poolSize;
    @Value("${unic.defaultExecutor.queue-capacity}")
    private Integer queueCapacity;


}
