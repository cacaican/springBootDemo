package com.xiaocai.springboot.integration.cache.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/14 16:57
 */
@Configuration
@ComponentScan
@EnableAsync
/*@EnableElasticsearchRepositories(
        basePackages = {"com.chinalife.base"}
)*/
public class RedisConfig {

    /*读取配置文件，获取redis配置的连接参数*/
    @Value("${redis.corePoolSize}")
    private Integer corePoolSize;
    @Value("${redis.maxPoolSize}")
    private Integer maxPoolSize;
    @Value("${redis.keepAliveSeconds}")
    private Integer keepAliveSeconds;
    @Value("${redis.queueCapacity}")
    private Integer queueCapacity;

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


    @Autowired
    @Qualifier("stringRedisSerializer")
    private RedisSerializer stringRedisSerializer;
    @Autowired
    @Qualifier("genericJackson2JsonRedisSerializer")
    private GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer;

    //自定义连接工厂
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

    //自定义线程池
    @Bean
    public TaskExecutor redisCacheMessageTaskExecutor() {
        ThreadPoolTaskExecutor messageTaskExecutor = new ThreadPoolTaskExecutor();
        messageTaskExecutor.setCorePoolSize(this.corePoolSize);
        messageTaskExecutor.setMaxPoolSize(this.maxPoolSize);
        messageTaskExecutor.setKeepAliveSeconds(this.keepAliveSeconds);
        messageTaskExecutor.setQueueCapacity(this.queueCapacity);
        return messageTaskExecutor;
    }

    //自定义监听器容器
    /*上面介绍了Spring默认的线程池simpleAsyncTaskExecutor，但是Spring更加推荐我们开发者使用ThreadPoolTaskExecutor类来创建线程池，其本质是对java.util.concurrent.ThreadPoolExecutor的包装。
    这个类则是spring包下的，是Spring为我们开发者提供的线程池类，这里重点讲解这个类的用法。
    Spring提供了xml给我们配置ThreadPoolTaskExecutor线程池，但是现在普遍都在用SpringBoot开发项目，所以直接上yaml或者properties配置即可，或者也可以使用@Configuration配置也行，下面演示配置和使用。*/
    @Bean
    public RedisMessageListenerContainer redisCacheMessageContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(this.jedisConnectionFactory());
        container.setTaskExecutor(this.redisCacheMessageTaskExecutor());
        container.setTopicSerializer(this.stringRedisSerializer);
        return container;
    }

    public static enum RedisMode {
        standalone,
        sentinel,
        cluster;

        private RedisMode() {
        }
    }

    @Bean(name = "myRedisTemplate")
    public RedisTemplate<String,Object>  getRedisTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(this.jedisConnectionFactory());
        redisTemplate.setEnableTransactionSupport(false);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);

//        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);

        return redisTemplate;

    }
}
