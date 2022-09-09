package com.xiaocai.base.proxyDemo;

import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName proxyDemo2
 * @Description 展示cglib动态代理
 * @Author xiaocai
 * @Date 2022/3/4
 */
public class proxyDemo2 {

    public static void main(String[] args) {
        proxyDemo2 proxyDemo2 = new proxyDemo2();
        System.out.printf("SimpleName是\t---%s%n",proxyDemo2.getClass().getSimpleName());
        System.out.printf("CanonicalName是\t---%s%n",proxyDemo2.getClass().getCanonicalName());
        System.out.printf("TypeName是\t---%s%n",proxyDemo2.getClass().getTypeName());

        //创建增强
        Enhancer enhancer = new Enhancer();
        //设置代理类的父类
        enhancer.setSuperclass(Demo1.class);
        //设置回调
        enhancer.setCallback(new Demo1Interceptor());
        //设置代理类对象
        Demo1 demo1 = (Demo1) enhancer.create();

        demo1.hello();



    }
}

class Demo1{

    private String name;
    private List children;
    private Map map;
    private Student student;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    void hello(){
        System.out.println("你好呀");
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
class Demo1Interceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.printf("o\t---%s%n",o.getClass().getTypeName());
        System.out.println("代理前");
//        Object invoke = methodProxy.invoke(o, objects);
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("代理后");

        return invoke;
    }
}

