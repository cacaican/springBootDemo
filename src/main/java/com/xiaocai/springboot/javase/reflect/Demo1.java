package com.xiaocai.springboot.javase.reflect;

import com.xiaocai.springboot.javase.reflect.entity.Course;
import com.xiaocai.springboot.javase.reflect.entity.Student2;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/21 9:14
 */
public class Demo1 {
    public static void main(String[] args) throws NoSuchFieldException {


        

        try {
            //step1 获取Class类文件
            //1.根据对象获取
            //2.根据全类名获取
            Class studentClass = getClassObj("com.xiaocai.springboot.javase.reflect.entity.Student",null);

            //step2 实例化对象
            Student2 student = (Student2) getReflectObj(studentClass);

            //step3 给属性赋值
            Field[] declaredField = studentClass.getDeclaredFields();

            Boolean b = setFieldValue(student ,"name" ,"xiaocai");
            //stop4 获取属性值
            Object fieldValue = getFieldValue(student ,"name" );
            System.out.println(fieldValue);

            //step5 执行执行方法
            Method exam = studentClass.getDeclaredMethod("exam", String.class, Course.class);
            Object returnObj =exam.invoke(student,student.getName(),new Course("数学","80"));
            System.out.println(returnObj);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }




    /**
     * @param className  反射对象的全类名
     * @param obj 反射对象实例
     * @return 反射对象的Class类
     */
    private static Class getClassObj(String className,Object obj) throws ClassNotFoundException {
        Class<?> name ;
        //优先使用类的全路径
        if (StringUtils.isNotBlank(className)){
            name = Class.forName(className);
            return name;
        }
        if (obj != null){
            name = obj.getClass();
            return name;
        }
        return null;
    }

    /**
     * @param classObj  实例类的Class对象
     * @return 获取实例化对象
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private static Object getReflectObj(Class classObj) throws InstantiationException, IllegalAccessException {
        Object o = classObj.newInstance();

        return o;
    }

    /**
     * 利用反射获取对象值 ,必须适用getDeclaredField方法，getFiled方法只能获取public修饰的成员属性
     * @param obj  实例对象
     * @param fieldName 字段名称
     * @return 字段值
     */
    private static Object getFieldValue(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        //修改访问权限，避免private修饰属性无法获取到
        field.setAccessible(true);
        Object o = field.get(obj);
        return o ;
    }

    /**
     * 利用反射给对象赋值,必须适用getDeclaredField方法，getFiled方法只能获取public修饰的成员属性
     * @param obj 实例对象
     * @param fieldName 属性名称
     * @param value 要设置的属性值
     * @return 操作成功与否，true表示操作成功
     */
    private static Boolean setFieldValue(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field =obj.getClass().getDeclaredField(fieldName);
        //修改访问权限，避免private修饰属性无法获取到
        field.setAccessible(true);
        field.set(obj, value);
        return false;
    }


}
