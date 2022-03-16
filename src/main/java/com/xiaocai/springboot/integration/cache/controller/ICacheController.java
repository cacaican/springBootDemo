package com.xiaocai.springboot.integration.cache.controller;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/15 16:51
 */
//@RestController
public interface ICacheController {


    void removeCache(@RequestParam("cacheName") String cacheName, HttpServletResponse var2);

    List getCache(@RequestParam("cacheName") String cacheName, HttpServletResponse var2);

    void addCache(@RequestParam("cacheName") String cacheName, @RequestParam("value")String  value);

    Map<String,Object> scanCache(@RequestParam("cacheName") String cacheName);

}
