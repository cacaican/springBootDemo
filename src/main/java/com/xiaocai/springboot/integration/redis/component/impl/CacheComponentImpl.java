package com.xiaocai.springboot.integration.redis.component.impl;

import com.xiaocai.springboot.integration.redis.component.CacheComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/15 17:15
 */
@Component
public class CacheComponentImpl implements CacheComponent {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void removeCache(String cacheName) {
        redisTemplate.delete(cacheName);
    }

    @Override
    public List getCache(String cacheName) {

        //1、通过redisTemplate设置值
        String str1 = (String) redisTemplate.boundValueOps(cacheName).get();
        /*
        //2、通过BoundValueOperations获取值
        BoundValueOperations stringKey = redisTemplate.boundValueOps("cacheName");
        String str2 = (String) stringKey.get();

        //3、通过ValueOperations获取值
        ValueOperations ops = redisTemplate.opsForValue();
        String str3 = (String) ops.get("StringKey");
        */

        ArrayList<Object> list = new ArrayList<>();
        list.add(str1);
        return list;
    }

    @Override
    public void addCache(String cacheName,String value) {


        //1、通过redisTemplate设置值
        redisTemplate.boundValueOps(cacheName).set(value);
//        redisTemplate.boundValueOps(cacheName).set(value,1, TimeUnit.MINUTES);

  /*      //2、通过BoundValueOperations设置值
        BoundValueOperations stringKey = redisTemplate.boundValueOps("StringKey");
        stringKey.set("StringVaule");
        stringKey.set("StringValue",1, TimeUnit.MINUTES);

        //3、通过ValueOperations设置值
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("StringKey", "StringVaule");
        ops.set("StringValue","StringVaule",1, TimeUnit.MINUTES);*/

    }
}
