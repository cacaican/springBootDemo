package com.xiaocai.springboot.javase.collection.set;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/27 9:40
 */
public class HashSetDemo {

    public static void main(String[] args) {
        HashSet<Object> hashSet = new HashSet<>();
        hashSet.add("sfaf");
        hashSet.add("null");
        hashSet.add("null");

        hashSet.add(null);
        hashSet.add(null);

        itHashset(hashSet);
        System.out.println(hashSet.size());//允许空值不允许重复值

    }

    private static void itHashset(HashSet<Object> hashSet) {
        Iterator<Object> iterator = hashSet.iterator();
        while(iterator.hasNext()) {
            Object next = iterator.next();
        }

    }
}
