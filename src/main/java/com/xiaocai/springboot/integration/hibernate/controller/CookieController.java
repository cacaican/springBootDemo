package com.xiaocai.springboot.integration.hibernate.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 测试后端对cookie的相关操作
 * @author: xiaocai
 * @time: 2022/3/14 17:17
 */
public interface CookieController {

    String getHttpCookie1(@CookieValue(value = "username",
            defaultValue = "sb") String username);


    String getAllCookies(HttpServletRequest request, HttpServletResponse response);


    String setHttpCookie2(HttpServletRequest request, HttpServletResponse response);

    String setCookieExpireTime(HttpServletRequest request, HttpServletResponse response);

    String deleteCookie(HttpServletRequest request, HttpServletResponse response);

    String httpOnlyCookie(HttpServletRequest request, HttpServletResponse response);
}
