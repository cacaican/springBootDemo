package com.xiaocai.base.proxyDemo;

import org.springframework.cglib.beans.BeanMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 本类展示cglib包中的beanMap类，可以将java类的成员属性转换成集合的形式作为解析
 * @author: xiaocai
 * @time: 2022/3/4 15:41
 */
public class BeanMapDemo {
    public static void main(String[] args) {
        //将bean中的属性转换称map
        Demo1 demo11 = new Demo1();
        demo11.setName("dd");
        ArrayList arrayList = new ArrayList();
        arrayList.add("dsfa");
        arrayList.add("dfgfd");
        arrayList.add("sgsfd");
        demo11.setChildren(arrayList);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("1","2");
        map.put("3","4");
        demo11.setMap(map);
        Student student = new Student();
        student.setAge(45);
        student.setName("小明");
        student.setSex("xiaocai");
        demo11.setStudent(student);
        BeanMap beanMap = BeanMap.create(demo11);
        Map submap =(Map) beanMap.get("map");
        //  这行报错，student无法转换为map，说明BeanMap转换的时候没法深层转换成员对象
        //        Map stu = (Map) beanMap.get("student");
        //        map.forEach((k,v)-> System.out.printf("%s---%s%n"));
        submap.forEach((k,v)-> System.out.printf("%s---%s%n",k,v));


//        BeanCopier.create(PlcInsurantEO.class, CustomerVO.class, false);
    }
}
