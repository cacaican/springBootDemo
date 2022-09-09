package com.xiaocai.base.ThreadDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Call {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        CallUp callUp = new CallUp();
        new Thread(()->{ for (int i = 0; i < 100; i++) callUp.printA();}, "A").start();
        new Thread(()->{ for (int i = 0; i < 100; i++) callUp.printB();}, "B").start();
        new Thread(()->{ for (int i = 0; i < 100; i++) callUp.printC();}, "C").start();




    }
}


/**
 * 使用juc中的lock来实现轮流打印
 * A->B->C->A
 */

class CallUp {
    int number = 1;
    Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();//用来监视A
    Condition conditionB = lock.newCondition();//用来监视B
    Condition conditionC = lock.newCondition();//用来监视C


    public void printA() {
        lock.lock();
        try {
            //业务代码
            //1.判断
            while (number != 1) {
                conditionA.await();
            }
            //2.执行
            System.out.println(Thread.currentThread().getName()+"->执行");
            //3.通知
            number = 2;
            conditionB.signal();//唤醒B

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printB() {
        lock.lock();
        try {
            //业务代码
            //1.判断
            while (number != 2) {
                conditionB.await();
            }
            //2.执行
            System.out.println(Thread.currentThread().getName()+"->执行");
            //3.通知
            number = 3;
            conditionC.signal();//唤醒C

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            //业务代码
            //1.判断
            while (number != 3) {
                conditionC.await();
            }
            //2.执行
            System.out.println(Thread.currentThread().getName()+"->执行");
            //3.通知
            number = 1;
            conditionA.signal();//唤醒A

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
