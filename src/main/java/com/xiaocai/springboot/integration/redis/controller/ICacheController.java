package com.xiaocai.springboot.integration.redis.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/15 16:51
 */
@RestController
public interface ICacheController {


    @RequestMapping({"/removeCache"})
    void removeCache(@RequestParam("cacheName") String cacheName, HttpServletResponse var2);

    @RequestMapping({"/getCahche"})
    List getCahche(@RequestParam("cacheName") String cacheName, HttpServletResponse var2);

    @RequestMapping({"/addCahche"})
    void addCahche(@RequestParam("addCahche") String cacheName, String  value);

}
