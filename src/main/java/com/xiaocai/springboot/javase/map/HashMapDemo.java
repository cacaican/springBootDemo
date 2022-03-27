package com.xiaocai.springboot.javase.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/27 9:50
 */
public class HashMapDemo {

    public static void main(String[] args) {
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("xiaocai","Niupi");
        hashMap.put("xiaocai","haha");
        hashMap.put("zhongkeruan","dd");
        hashMap.put(null,"dd");//允许key为空，递进是0.75

        System.out.println(hashMap.size());


        Set<Map.Entry<String, Object>> entries = hashMap.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
        }
        for (Map.Entry entry :hashMap.entrySet()){
            Object key = entry.getKey();
        }
        for (String key :hashMap.keySet()){
            System.out.println( key+"-----"+hashMap.get(key));
           ;
        }

        
    }
}
