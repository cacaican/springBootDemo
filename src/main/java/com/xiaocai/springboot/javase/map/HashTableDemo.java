package com.xiaocai.springboot.javase.map;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/27 9:57
 */
public class HashTableDemo {
    public static void main(String[] args) {
        Hashtable<Object, Object> hashtable = new Hashtable<>();
        hashtable.put("xiaocai","Niupi");
        hashtable.put("xiaocai","haha");
        hashtable.put("zhongkeruan","dd");
        hashtable.put(null,"dd");//允许key为空,不然报错
        System.out.println(hashtable.size());


    }
}
