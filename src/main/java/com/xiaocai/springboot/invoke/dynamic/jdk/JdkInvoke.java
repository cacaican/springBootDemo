package com.xiaocai.springboot.invoke.dynamic.jdk;

import com.xiaocai.springboot.invoke.entity.People;
import com.xiaocai.springboot.invoke.entity.Student;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: 这里就是创建代理类实现InvocationHandler ，然后引入待代理的对象，实现invoke方法
 * 要使用的时候就Proxy.newProxyInstance(代理类的Class，被代理类的接口.class,代理类实例)，获取接口的实例
 * @author: xiaocai
 * @time: 2022/3/21 14:15
 */
public class JdkInvoke implements InvocationHandler {

    //被代理对象，Object类型
    private Object target;

    public JdkInvoke(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("准备向数据库中插入数据");
        Object returnvalue = method.invoke(target, args);
        System.out.println("插入数据库成功");

        return returnvalue;
    }

    public static void main(String[] args) {
        Student student = new Student();
        JdkInvoke jdkInvoke = new JdkInvoke(student);
        People people = (People) Proxy.newProxyInstance(jdkInvoke.getClass().getClassLoader(), student.getClass().getInterfaces(), jdkInvoke);
        people.eat();
    }

}
