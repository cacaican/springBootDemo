package com.xiaocai.springboot.javase.xml;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/27 11:52
 */
public class PathDemo {

    public static void main(String[] args) throws IOException {
        PathDemo pathDemo = new PathDemo();
        pathDemo.getPath();

    }

    private  void getPath() throws IOException {
        // 第一种：获取类加载的根路径
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println(f);//E:\workspace\GitHub\springiA4_code-master\springbootDemo\target\classes

        // 获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录  E:\workspace\GitHub\springiA4_code-master\springbootDemo\target\classes\com\xiaocai\springboot\javase\xml
        File f2 = new File(this.getClass().getResource("").getPath());
        System.out.println(f2);

        // 第二种：获取项目路径    E:\workspace\GitHub\springiA4_code-master\springbootDemo
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);


        // 第三种： file:/E:/workspace/GitHub/springiA4_code-master/springbootDemo/target/classes/
        URL xmlpath = this.getClass().getClassLoader().getResource("");
        System.out.println(xmlpath);


        // 第四种：E:\workspace\GitHub\springiA4_code-master\springbootDemo
        System.out.println(System.getProperty("user.dir"));
        /*
         * 结果： C:\Documents and Settings\Administrator\workspace\projectName
         * 获取当前工程路径
         */

        // 第五种：  获取所有的类路径 包括jar包的路径
        System.out.println(System.getProperty("java.class.path"));
    }


}
