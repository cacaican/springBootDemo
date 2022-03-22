package com.xiaocai.springboot.integration.ssh.controller.impl;

import com.xiaocai.springboot.integration.ssh.controller.CookieController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/14 17:22
 */
@RestController
@RequestMapping("/cookie")
public class CookieControllerImpl implements CookieController {

    @Override
    @RequestMapping("/getHttpCookie1")
    public String getHttpCookie1(@CookieValue(value = "username",
            defaultValue = "sb")String username) {
        System.out.println("使用@CookieValue获取cookie userName的值为"+username);
        return username;
    }

    @Override
    @RequestMapping("/getAllCookies")
    public String getAllCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .map(c -> c.getName() + "=" + c.getValue())
                    .collect(Collectors.joining(", "));
        }
        return "No cookies";
    }

    @Override
    @RequestMapping("/setHttpCookie2")
    public String setHttpCookie2(HttpServletRequest request, HttpServletResponse response) {

        Cookie cookie = new Cookie("我的cookie","我的cookie的新值");
        response.addCookie(cookie);
        return null;
    }

    @Override
    @RequestMapping("/setCookieExpireTime")
    public String setCookieExpireTime(HttpServletRequest request, HttpServletResponse response) {

        // 创建一个 cookie对象
        Cookie cookie = new Cookie("username", "Jovan");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7天过期

        //将cookie对象加入response响应
        response.addCookie(cookie);

        return null;
    }

    @Override
    @RequestMapping("/deleteCookie")
    public String deleteCookie(HttpServletRequest request, HttpServletResponse response) {



        // 将Cookie的值设置为null
        Cookie cookie = new Cookie("username", null);
        //将`Max-Age`设置为0
        cookie.setMaxAge(0);
        return null;
    }

    @Override
    @RequestMapping("/httpOnlyCookie")
    public String httpOnlyCookie(HttpServletRequest request, HttpServletResponse response) {
        //要删除Cookie，需要将Max-Age设置为0，并且将Cookie的值设置为null。不要将Max-Age指令值设置为-1负数。否则，浏览器会将其视为会话cookie
        // 创建一个 cookie对象
        Cookie cookie = new Cookie("username", "Jovan");
        cookie.setHttpOnly(true);  //不能被js访问的Cookie

        //将cookie对象加入response响应
        response.addCookie(cookie);
        return null;
    }
}
