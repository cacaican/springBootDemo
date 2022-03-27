package com.xiaocai.springboot.integration.rules.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/21 14:59
 */
public class GroovyDemo {


    public static void main(String[] args) throws IOException, ScriptException, ResourceException {
        testGroovy1();
        testGroovy2();
        testGroovy3();


    }

    /**
     *使用groovyShell
     * 1.创建GroovyShell ：new GroovyShell（bingding）
     * 2.执行. groovyShell2.evaluate(File (脚本文件))
     * @throws IOException
     */
    private static void testGroovy3() throws IOException {
        // 调用带参数的groovy shell时，使用bind绑定数据
        Binding binding = new Binding();
        binding.setProperty("name", "Juxinli");

        GroovyShell groovyShell2 = new GroovyShell(binding);
        Object result2 = groovyShell2.evaluate(new File("src/main/java/com/xiaocai/springboot/integration/rule/groovy/shells/shell3.groovy"));
        System.out.println(result2.toString());

    }


    /*
    * 通过GroovyClassLoader动态加载Groovy Class
    * 1.新建一个groovy加载器，new GroovyClassLoader(一个类加载器，CompilerConfiguration实例)
    * 2.解析class， groovyClassLoader.parseClass(groovyFile)
    * 3.创建groovy类的实例对象-(GroovyObject) groovyClass.newInstance()
    * 4.执行groovy中的方法 groovyObject.invokeMethod(”方法名“，数组类型的参数列表）
    * */
    private static void testGroovy2() throws IOException {
        String result = "";


        CompilerConfiguration config = new CompilerConfiguration();
        config.setSourceEncoding("UTF-8");
        // 设置该GroovyClassLoader的父ClassLoader为当前线程的加载器(默认)
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader(), config);

        File groovyFile = new File("src/main/java/com/xiaocai/springboot/integration/rule/groovy/shells/shell2.groovy");
        if (!groovyFile.exists()) {
            return ;
        }

        try {
            // 获得GroovyShell_2加载后的class
            Class<?> groovyClass = groovyClassLoader.parseClass(groovyFile);
            // 获得GroovyShell_2的实例
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            // 反射调用sayHello方法得到返回值
            Object methodResult = groovyObject.invokeMethod("sayHello", new Object[] {"张三", "男", 25});
            if (methodResult != null) {
                result = methodResult.toString();
            }
        } catch (Exception e) {
            System.out.println("加载groovy类失败");
        }

   }

   /*
   * 使用GroovyScriptEngine脚本引擎加载Groovy脚本
   * 1.创建GroovyScriptEngine ： new GroovyScriptEngine("脚本路径名")
   * 2. engine.run("脚本名称", binding)
   * */
    private static void testGroovy1() throws ScriptException, ResourceException, IOException {
        // GroovyScriptEngine的根路径，如果参数是字符串数组，说明有多个根路径
        GroovyScriptEngine engine = new GroovyScriptEngine("src/main/java/com/xiaocai/springboot/integration/rule/groovy/shells/shell1.groovy");

        Binding binding = new Binding();
        binding.setVariable("name", "xiaocai");

        Object result1 = engine.run("shell1.groovy", binding);
        System.out.println(result1);

    }
}
