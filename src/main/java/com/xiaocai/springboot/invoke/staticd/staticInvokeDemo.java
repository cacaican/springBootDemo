package com.xiaocai.springboot.invoke.staticd;

import com.xiaocai.springboot.invoke.entity.People;
import com.xiaocai.springboot.invoke.entity.Student;

/**
 * @description: 静态代理，所谓静态代理，就是引入被代理对象，在调用代理方法前加入自己的操作
 * @author: xiaocai
 * @time: 2022/3/21 14:01
 */
public class staticInvokeDemo implements People {

    private Student stu = new Student() ;

    @Override
    public void eat() {
        System.out.println("学生代理吃饭前给学生洗手");
        stu.eat();
        System.out.println("学生代理吃饭后放盘子");

    }

    @Override
    public void shit() {
        System.out.println("拉前");
        stu.shit();
        System.out.println("拉后");
    }

    public static void main(String[] args) {
        staticInvokeDemo staticInvokeDemo = new staticInvokeDemo();
        staticInvokeDemo.eat();
        staticInvokeDemo.shit();

    }
}
