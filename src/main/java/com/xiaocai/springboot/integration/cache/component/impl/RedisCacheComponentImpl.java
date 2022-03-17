package com.xiaocai.springboot.integration.cache.component.impl;

import com.google.common.collect.Sets;
import com.xiaocai.springboot.integration.cache.component.RedisCacheComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;
import redis.clients.jedis.util.JedisClusterCRC16;


import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/15 17:15
 */
@Component
public class RedisCacheComponentImpl implements RedisCacheComponent {

//    @Qualifier("myRedisTemplate")
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public void removeCache(String cacheName) {
        redisTemplate.delete(cacheName);
    }

    @Override
    public List getCache(String cacheName) {

        //1、通过redisTemplate获取值
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


        //1、通过redisTemplate设置值，并设置过期事件
//        redisTemplate.boundValueOps(cacheName).set(value);
        redisTemplate.boundValueOps(cacheName).set(value,1, TimeUnit.MINUTES);
        String str1 = (String) redisTemplate.boundValueOps(cacheName).get();


  /*      //2、通过BoundValueOperations设置值
        BoundValueOperations stringKey = redisTemplate.boundValueOps("StringKey");
        stringKey.set("StringVaule");
        stringKey.set("StringValue",1, TimeUnit.MINUTES);

        //3、通过ValueOperations设置值
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("StringKey", "StringVaule");
        ops.set("StringValue","StringVaule",1, TimeUnit.MINUTES);*/

    }

    @Override
    public Map<String,Object> scanCache(String cacheName) {
        HashMap<String, Object> result = new HashMap<>();
        List<RedisClientInfo> clientList = redisTemplate.getClientList();
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        RedisConnection connection = connectionFactory.getConnection();
//        Jedis jedis = (Jedis) connection.getNativeConnection();
//        Set<Object> set = Sets.newHashSet();
//        Set keys;
//        //获取所有匹配的keys
//        keys = getScan(jedis,cacheName);
//        Iterator iterator = keys.iterator();
//        //根据key获取所有的values
//        while (iterator.hasNext()) {
//            //todo
//        }
//        return set;

        ScanOptions scanOptions = ScanOptions.scanOptions().count(6000).match("*"+cacheName + "*").build();
        Cursor<byte[]> cursors = connection.scan(scanOptions);
        cursors.forEachRemaining(c ->
                result.put(new String(c, Charset.forName("UTF-8")),redisTemplate.boundValueOps(new String(c, Charset.forName("UTF-8"))).get())
        );
//        cursors.forEachRemaining(new Consumer<byte[]>() {
//            @Override
//            public void accept(byte[] bytes) {
//                String key = new String(bytes, StandardCharsets.UTF_8);
//                System.out.println(key);
//
//            }
//        });
        connection.close();
        return  result;
    }

   /* public Set<Object> scanValue(String pattern, RedisOperations<?, ?> redisCache) {
        return (Set)redisCache.execute((connection) -> {
            Set<Object> keys = Sets.newHashSet();
            connection.getNativeConnection();
            MultiKeyCommands multiKeyCommands = (MultiKeyCommands)commands;
            ScanParams scanParams = new ScanParams();
            scanParams.match(pattern);
            scanParams.count(1000);

            for(ScanResult scan = multiKeyCommands.scan("0", scanParams); null != scan.getCursor(); scan = multiKeyCommands.scan(scan.getStringCursor(), scanParams)) {
                keys.addAll(scan.getResult());
                if (StringUtils.equals("0", scan.getCursor())) {
                    break;
                }
            }

            return keys;
        });
    }*/

    
    public Set scanClusterCache(String cacheName) {
        Set<Object> list = Sets.newHashSet();
        RedisClusterConnection redisClusterConnection = redisTemplate.getConnectionFactory().getClusterConnection();
        Map<String, JedisPool> clusterNodes = ((JedisCluster)redisClusterConnection.getNativeConnection()).getClusterNodes();
        Iterator clusterNodesEntry = clusterNodes.entrySet().iterator();

        while(true) {
            Set keys;
            do {
                Jedis jedis;
                do {
                    if (!clusterNodesEntry.hasNext()) {
                        return list;
                    }

                    Map.Entry<String, JedisPool> entry = (Map.Entry)clusterNodesEntry.next();
                    jedis = ((JedisPool)entry.getValue()).getResource();
                } while(jedis.info("replication").contains("role:slave"));

                keys = getScan(jedis, cacheName);
            } while(keys.size() <= 0);

            Map<Integer, List<String>> map = new HashMap(8);
            Iterator var11 = keys.iterator();

            while(var11.hasNext()) {
                String key = (String)var11.next();
                int slot = JedisClusterCRC16.getSlot(key);
                if (map.containsKey(slot)) {
                    ((List)map.get(slot)).add(key);
                } else {
                    List<String> list1 = new ArrayList();
                    list1.add(key);
                    map.put(slot, list1);
                }
            }

            var11 = map.entrySet().iterator();

            while(var11.hasNext()) {
                Map.Entry<Integer, List<String>> integerListEntry = (Map.Entry)var11.next();
                list.addAll((Collection)integerListEntry.getValue());
            }
        }
    }


    public static Set<String> getScan(Jedis redisService, String key) {
        Set<String> list = Sets.newHashSet();
        ScanParams params = new ScanParams();
        params.match(key);
        params.count(10);
        String cursor = "0";
        ScanResult<String> scanResult = redisService.scan(cursor, params);
        int num = 0;

        while(null != scanResult.getCursor()) {
            ++num;
            list.addAll(scanResult.getResult());
            if ("0".equals(scanResult.getCursor())) {
                break;
            }

            scanResult = redisService.scan(cursor, params);
            cursor = scanResult.getCursor();
            if (num > 100000) {
                break;
            }
        }

        return list;
    }


}
