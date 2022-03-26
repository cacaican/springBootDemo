package com.xiaocai.springboot.javase.dataType;

import com.xiaocai.springboot.javase.invoke.entity.Student;

import java.util.Date;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/26 16:58
 */
public class Test {
    public static void main(String[] args) throws RuntimeException {
        Integer a = new Integer(5);
        Integer b = 5;
        int c = 5 ;
        System.out.println(a==b);  //false  这里自动拆箱了
        System.out.println(b instanceof  Integer); //true
        System.out.println(c==b); //true
        Integer a1 = new Integer(220);
        Integer b1 = 220;
        System.out.println(a1==b1);  //false

        Object o = new Object();
        o.equals(new Object());

        StringBuffer sdf = new StringBuffer( "sdf");
        StringBuffer append = sdf.append("d");
        System.out.println(sdf == append);
        String str = new String ("fsdf");
        String str2 = str +="df";
        System.out.println(str ==str2);
        String as = new String("121");
        String ah = new String("121");
        String aa ="121";
        String ab ="121";
        String ac ="12"+"1";
        String ad ="12";
        String af ="1";
        String ag =af+ad;
        System.out.println(as == ah); //false
        System.out.println(as == aa); //false
        System.out.println(aa == ab); //true
        System.out.println(aa == ac); //true
        System.out.println(aa == ag); //false
        System.out.println(aa == (ag.intern())); //false
        ClassLoader classLoader = ah.getClass().getClassLoader();
        System.out.println(classLoader);

        Student student = new Student();
        student.setAge(15);
        System.out.println(student.getClass().getClassLoader());
        Date date = new Date();
        System.out.println(date);

        System.out.println(0.3*3);
        System.out.println(3.14*5);



        throw new RuntimeException();
    }
}
