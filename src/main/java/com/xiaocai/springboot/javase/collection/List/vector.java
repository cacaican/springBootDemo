package com.xiaocai.springboot.javase.collection.List;

import java.util.Vector;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/27 9:47
 */
public class vector {

    public static void main(String[] args) {
        Vector<Object> objects = new Vector<>();
        objects.add(null);
        objects.add(null);

        objects.add("sdf");
        objects.add("sdf");
        System.out.println(objects.size());
        for (int i = 0; i < objects.size(); i++) {

        }
    }
}
