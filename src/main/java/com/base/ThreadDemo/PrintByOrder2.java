package com.xiaocai.base.ThreadDemo;

/**
 * @description: 多线程轮流输出1-100
 * 问题分析，1.多个线程间如何拿到同一个int值2.多个int如何共享当前int属性 3.线程之间如何控制谁先谁后 4.如何保证各个线程之间互不阻塞
 * 1.使用内部类，内部类可获取外部类的成员属性
 * 2.使用volatile修饰符，该修饰符可使i 属性在线程间共享数据
 * 3.各个类中输出加if判断
 * 4.
 * @author: xiaocai
 * @time: 2022/3/3 17:17
 */
public class PrintByOrder2 {

    public static void main(String[] args) {

    }


    synchronized  void print1 () {


    }
}



