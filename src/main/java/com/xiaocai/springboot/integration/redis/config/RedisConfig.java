package com.xiaocai.springboot.integration.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/14 16:57
 */
public class RedisConfig {

    @Value("${redis.corePoolSize}")
    private Integer corePoolSize;
    @Value("${redis.maxPoolSize}")
    private Integer maxPoolSize;
    @Value("${redis.keepAliveSeconds}")
    private Integer keepAliveSeconds;
    @Value("${redis.queueCapacity}")
    private Integer queueCapacity;
    @Autowired
    @Qualifier("stringRedisSerializer")
    private RedisSerializer stringRedisSerializer;
    @Value("${redis.mode}")
    private String mode;
    @Value("${redis.clusterNodes}")
    private String clusterNodes;
    @Value("${redis.master}")
    private String master;
    @Value("${redis.sentinelHostAndPorts}")
    private String sentinelHostAndPorts;
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.database}")
    private int database;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.usePool}")
    private boolean usePool;
    @Value("${redis.maxTotal}")
    private int maxTotal;
    @Value("${redis.maxIdle}")
    private int maxIdle;
    @Value("${redis.minIdle:5}")
    private int minIdle;
    @Value("${redis.maxWaitMillis}")
    private int maxWaitMillis;
    @Value("${redis.testOnBorrow:true}")
    private boolean testOnBorrow;

    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(this.maxIdle);
        jedisPoolConfig.setMaxTotal(this.maxTotal);
        jedisPoolConfig.setMinIdle(this.minIdle);
        jedisPoolConfig.setMaxWaitMillis((long)this.maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(this.testOnBorrow);
        JedisConnectionFactory connectionFactory = null;
        if (RedisMode.standalone.name().equals(this.mode)) {
            connectionFactory = new JedisConnectionFactory(jedisPoolConfig);
            connectionFactory.setHostName(this.host);
            connectionFactory.setPort(this.port);
            connectionFactory.setDatabase(this.database);
            connectionFactory.setPassword(this.password);
            connectionFactory.setUsePool(this.usePool);
            connectionFactory.setTimeout(this.maxWaitMillis);
        } else if (RedisMode.sentinel.name().equals(this.mode)) {
            if (StringUtils.isNotEmpty(this.master) && StringUtils.isNotEmpty(this.sentinelHostAndPorts)) {
                Set<String> setSentinelHostAndPorts = new HashSet();
                String[] arrSentinels = StringUtils.split(this.sentinelHostAndPorts, ",");
                if (arrSentinels != null && arrSentinels.length > 0) {
                    String[] var5 = arrSentinels;
                    int var6 = arrSentinels.length;

                    for(int var7 = 0; var7 < var6; ++var7) {
                        String sentinel = var5[var7];
                        setSentinelHostAndPorts.add(sentinel);
                    }

                    RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration(this.master, setSentinelHostAndPorts);
                    sentinelConfig.sentinel(this.host, this.port);
                    connectionFactory = new JedisConnectionFactory(sentinelConfig, jedisPoolConfig);
                    connectionFactory.setDatabase(this.database);
                    connectionFactory.setPassword(this.password);
                    connectionFactory.setUsePool(this.usePool);
                    connectionFactory.setTimeout(this.maxWaitMillis);
                }
            }
        } else if (RedisMode.cluster.name().equals(this.mode) && StringUtils.isNotEmpty(this.clusterNodes)) {
            String[] arrClusterNodes = StringUtils.split(this.clusterNodes, ",");
            if (arrClusterNodes != null && arrClusterNodes.length > 0) {
                RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration(Arrays.asList(arrClusterNodes));
                connectionFactory = new JedisConnectionFactory(clusterConfig, jedisPoolConfig);
                connectionFactory.setPassword(this.password);
                connectionFactory.setTimeout(this.maxWaitMillis);
            }
        }

        return connectionFactory;
    }

    @Bean
    public TaskExecutor redisCacheMessageTaskExecutor() {
        ThreadPoolTaskExecutor messageTaskExecutor = new ThreadPoolTaskExecutor();
        messageTaskExecutor.setCorePoolSize(this.corePoolSize);
        messageTaskExecutor.setMaxPoolSize(this.maxPoolSize);
        messageTaskExecutor.setKeepAliveSeconds(this.keepAliveSeconds);
        messageTaskExecutor.setQueueCapacity(this.queueCapacity);
        return messageTaskExecutor;
    }

    @Bean
    public RedisMessageListenerContainer redisCacheMessageContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(this.jedisConnectionFactory());
        container.setTaskExecutor(this.redisCacheMessageTaskExecutor());
        container.setTopicSerializer(this.stringRedisSerializer);
        return container;
    }
}
