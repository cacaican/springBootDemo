package com.xiaocai.springboot.invoke.dynamic.cglib;

import com.xiaocai.springboot.invoke.entity.Student;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description: cglib代理，方法是创建一个类，实现MethodInterceptor，实现其中的intercept接口，里面调用methodProxy.invokeSuper方法可代理目标中的方法，
 * 在使用时候，new Enhance()
 * setSuperClass是要代理的方法，setCallback是刚刚实现的cglib代理类，
 * enhancer.create()可以创建类的代理，之后正常调用即可
 * @author: xiaocai
 * @time: 2022/3/21 14:36
 */
public class CglibInvoke implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("这里是对目标类进行增强！！！");
        //注意这里的方法调用，不是用反射哦！！！
        Object object = methodProxy.invokeSuper(o, objects);
        return object;
    }


    public static void main(String[] args) {

        //创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
        Enhancer enhancer = new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(Student.class);
        //设置回调函数
        enhancer.setCallback(new CglibInvoke());

        //这里的creat方法就是正式创建代理类
        Student studentInvoke = (Student)enhancer.create();
        //调用代理类的eat方法
        studentInvoke.eat();
    }


}
