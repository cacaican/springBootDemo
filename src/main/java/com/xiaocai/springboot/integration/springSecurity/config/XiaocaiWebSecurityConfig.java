package com.xiaocai.springboot.integration.springSecurity.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
/*import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;*/

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/15 9:33
 */
//@Configuration
public class XiaocaiWebSecurityConfig  /*extends WebSecurityConfigurerAdapter*/ {

    /**
     * 日志记录类
     */
//    private static final Logger LOGGER = LoggerFactory.getLogger(XiaocaiWebSecurityConfig.class);

//    @Autowired
//    private  PasswordEncoder passwordEncoder;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                /*authorizeRequests定义那些url需要被保护,那些不需要进行保护,通常这个出来在配置的第一行*/
////                .antMatchers("/500").permitAll()
//                /*antMatchers是设置路径,通常这里设置的是控制器(controller)上的请求路径*/
//                /*后面的permitAll是允许任何人访问*/
////                .antMatchers("/403").permitAll()
////               /*上面的500、403、404我都设置的是任何人可以访问,通常对公共的页面我们都设置的permitAll来进行访问的*/
////                .antMatchers("/404").permitAll()
////                .antMatchers("/admin/index").hasRole("ADMIN")//指定权限为ADMIN才能访问
////                .antMatchers("/person").hasAnyRole("ADMIN","USER")//指定多个权限都能访问
//                .anyRequest() //任何其它请求
//                .authenticated() //都需要身份认证
//                .and()
//                /*and()方法类似于xml配置中的结束标签，
//                and()方法返回的对象还是HttpSecurity,当我们配置完成一段配置就要使用and来结束*/
//                .formLogin() //使用表单认证方式
//                /*formLogin是采用的是表单认证方式,还有一个httpBasic认证,你应用应该不会用httpBasic进行认证吧,现在都是采用的formLogin来进行认证的。*/
//                .loginProcessingUrl("/login")//配置默认登录入口
//                /*loginProcessingUrl是配置默认的登录入口(这里强调一遍Spring security默认的处理登录接口是/login这个自带的接口)*/
//                .and()
//                .csrf().disable();
////                .logout();
//
//                /*csrf.disable是 关闭跨站请求伪造保护
//                 */
//    }
    /**
     * 自定义认证策略
     * @return
     */
/*    @Autowired
    public void  configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String password = passwordEncoder.encode("123456");

        LOGGER.info("加密前的密码:" + 123456);
        LOGGER.info("加密后的密码:" + password);

//        auth.inMemoryAuthentication().withUser("admin").password(password)
//                .roles("ADMIN").and();
        auth.inMemoryAuthentication().withUser("user").password(password)
                .roles("USER").and();

    }*/
}
