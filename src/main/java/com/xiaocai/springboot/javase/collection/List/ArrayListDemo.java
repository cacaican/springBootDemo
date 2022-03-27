package com.xiaocai.springboot.javase.collection.List;

import com.xiaocai.springboot.javase.invoke.entity.Student;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/27 9:33
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add("hahaha");
        arrayList.add("bbb");
        arrayList.add(5);
        arrayList.add(null);
        arrayList.add(null);
        System.out.println(arrayList.size());
        itar(arrayList);
    }

    private static void itar(ArrayList<Object> arrayList) {
        for (int i=0;i<arrayList.size();i++){

        }
        for (Object o :arrayList){

        }
        Iterator<Object> iterator = arrayList.iterator();
        while(iterator.hasNext()){
            Object next = iterator.next();


        }

    }

}
