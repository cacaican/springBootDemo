package com.xiaocai.base.proxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName proxyDemo
 * @Description 展示jdk基于接口的动态代理；首先时创建接口
 * @Author xiaocai
 * @Date 2022/3/3
 */
public class proxyDemo1 {

    public static void main(String[] args) {
        AnimalInterface animal = (AnimalInterface) MyProxy
                .getProxy(new AnimalImpl());
        animal.cry();
        animal.cry2();
    }
}

//step 1:创建一个接口，定义其抽象方法
interface AnimalInterface {
    void cry();

    void cry2();
}

//step2 ，创建接口的具体实现
class AnimalImpl implements AnimalInterface {

    public void cry() {
        System.out.println("crying");
    }

    public void cry2() {
        System.out.println("crying");
    }

}

//step3 ，创建代理类，内部必须实现invoke方法

/*
动态代理类：在程序运行时，通过反射机制动态生成。
动态代理类通常代理接口下的所有类。
动态代理事先不知道要代理的是什么，只有在运行的时候才能确定。
动态代理的调用处理程序必须事先InvocationHandler接口，及使用Proxy类中的newProxyInstance方法动态的创建代理类。
Java动态代理只能代理接口，要代理类需要使用第三方的CLIGB等类库
*/

class MyProxy implements InvocationHandler {
    private Object proxied;

    private MyProxy(Object proxied) {
        this.proxied = proxied;
    }

    public static Object getProxy(Object proxied) {
        return Proxy.newProxyInstance(proxied.getClass().getClassLoader(),
                proxied.getClass().getInterfaces(), new MyProxy(proxied));
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object ret;
        System.out.println("Before Method Invoke");
        ret = method.invoke(proxied, args);
        System.out.println("After Method Invoke");
        return ret;
    }
}