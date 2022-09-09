package com.xiaocai.base.reflectDemo;

import com.xiaocai.base.proxyDemo.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 展示java反射获取对象
 * 反射原理，
 * 1.编写源码文件，
 * 2.java命令对源码进行编译获得class字节码文件
 * 3.使用类加载器将字节码加载到内存
 * 4.获取到字节码
 * @author: xiaocai
 * @time: 2022/3/4 16:19
 */
public class Demo1 {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        //step1 获取字节码文件
        //方法1：直接使用对象.class
        Class<Student> studentClass1 = Student.class;
        //方法2,对象.getClass()
        Student student = new Student();
        Class<? extends Student> studentClass2 = student.getClass();
        //方法3 使用类加载器
        Class<?> studentClass3 = Class.forName("com.xiaocai.base.proxyDemo.Student");

        //step2 根据class字节码文件生成对象
        Student student1 = studentClass1.newInstance();
        Student student2 = studentClass2.newInstance();
        Student student3 = (Student) studentClass3.newInstance();

        //step3 设置对象的值
        //获取所有字段
        Field[] declaredFields = studentClass3.getDeclaredFields();
        Class<?>[] declaredClasses = studentClass3.getDeclaredClasses();
        Constructor<?> declaredConstructor = studentClass3.getDeclaredConstructor();
        Class<?> declaringClass = studentClass3.getDeclaringClass();

        List<Field> fields = Arrays.asList(declaredFields).stream().collect(Collectors.toList());
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
        }
        fields.forEach(declaredField -> System.out.printf("字段名%s--------字段类型%s--------------是否为字符串类型%s%n"
                , declaredField.getName(), declaredField.getType().getTypeName(), declaredField.getType().getName().equals(String.class.getTypeName())))
        ;
        //step4 获取对象的值并设置属性值
        fields.forEach(declaredField -> {
            try {
                declaredField.setAccessible(true);
                System.out.printf("反射值设置属性--属性名%s%n", declaredField.getName(), declaredField.getName());
                declaredField.set(student1, declaredField.getType().getName().equals(String.class.getName()) ? "50" : 50);
                System.out.printf("反射查看属性--属性名%s------属性值%s%n", declaredField.getName(), declaredField.get(student1));

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        //step5 获取方法 并执行方法
        Method[] declaredMethods = studentClass2.getDeclaredMethods();
        //排个序，设置set在前，get在后,不然反射验证时候会失败
        List<Method> methods = Arrays.asList(declaredMethods).stream().sorted(Comparator.comparing(Method ::getName,Comparator.reverseOrder() )).collect(Collectors.toList());
        methods.forEach(method -> {
            try {

                System.out.printf("反射查看方法--方法名%s----出参%s%n", method.getName(), method.getReturnType());
                Arrays.stream(method.getParameters()).sequential().forEach(p -> System.out.println(String.format("参数列表：  参数名%s--参数类型%s", p.getName(), p.getType().getName())));


                if ("setName".equals(method.getName())) {
                    method.invoke(student2, "xiaocai");
                    System.out.println("设置名称--" );
                }
                if ("getName".equals(method.getName())) {
                    String name = (String) method.invoke(student2);
                    System.out.println("获取到的名称为--" + name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });




    }
}
