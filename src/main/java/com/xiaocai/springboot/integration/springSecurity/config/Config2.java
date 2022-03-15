package com.xiaocai.springboot.integration.springSecurity.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/15 11:21
 */
@Configuration
public class Config2 {


/**
     * 日志记录类
     */

    private static final Logger LOGGER = LoggerFactory.getLogger(XiaocaiWebSecurityConfig.class);


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
