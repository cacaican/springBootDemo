package com.xiaocai.springboot.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@ComponentScan("com.xiaocai.springboot.login")
//@ComponentScan("com.xiaocai.springboot.integration")
@ComponentScan("com.xiaocai.springboot.integration.cache")
@ComponentScan("com.xiaocai.springboot.integration.hibernate")

@EntityScan(basePackages = "com.xiaocai.springboot.integration")
//数据库连接信息
@ImportResource(
        /*数据库连接信息，可以使用* 做通配符*/
        //*hikari数据源实例化配置
        {
        "classpath*:config/service-hakiri-config.xml"
        /* ,"classpath*:config/ehcache.xml"   xml头文件有错，启动不起来*/})
@EnableCaching//允许启用缓存
//主类首先开启EnableRedisHttpSession注解：支持spring-boot分布式事务
@EnableRedisHttpSession
public class SpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }

}
