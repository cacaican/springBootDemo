package com.xiaocai.springboot.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
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
@ComponentScan("com.xiaocai.springboot.integration.ssh")
@ComponentScan("com.xiaocai.springboot.integration.ssm")
@MapperScan("com.xiaocai.springboot.integration.ssm")
@ComponentScan("com.xiaocai.springboot.integration.rabbitmq")
@EntityScan(basePackages = "com.xiaocai.springboot.integration")//设置hiernate的entity扫包路径，找包中@entity和@table的class
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
