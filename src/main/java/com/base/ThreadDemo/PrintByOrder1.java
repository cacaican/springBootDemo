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
public class PrintByOrder1 {

    private volatile  int i = 10;

    private volatile  int flag = 0;
    private Thread a,b,c;



    //静态方法无法调用非静态成员变量
    //而子类中从全局变量i=10中是因为从父类继承下来的i=10
    public static void main(String[] args) {
        PrintByOrder1 printByOrder = new PrintByOrder1();
        printByOrder.testThread();
    }

    public void testThread(){
        a=new Thread(new A());
        b=new Thread(new B());
        c=new Thread(new C());
        a.start();
        b.start();
        c.start();
    }

    public class A implements Runnable {
        @Override
        public void run() {
            System.out.println(String.format("%s---%s","i的初始值为",i));

            if(flag ==0) {
                while (i<100) {
                    System.out.println(String.format("%s---%s",Thread.currentThread().getName(),i));
                    i++;
                    flag =1;
                }
            }

        }
    }

    public class C implements Runnable {
        @Override
        public void run() {
            System.out.println(String.format("%s---%s","i的初始值为",i));

            if(flag ==1) {
                while (i<100) {
                    System.out.println(String.format("%s---%s",Thread.currentThread().getName(),i));
                    i++;
                    flag =2;

                }
            }

        }
    }

    public class B implements Runnable {
        @Override
        public void run() {
            System.out.println(String.format("%s---%s","i的初始值为",i));

            if(flag ==2) {
                while (i<100) {
                    System.out.println(String.format("%s---%s",Thread.currentThread().getName(),i));
                    i++;
                    flag =0;
                }
            }

        }
    }
}

