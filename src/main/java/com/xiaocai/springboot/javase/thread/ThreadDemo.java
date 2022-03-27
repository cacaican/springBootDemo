package com.xiaocai.springboot.javase.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/26 22:51
 */
public class ThreadDemo {


    private Lock lockA= new ReentrantLock();
    private Lock lockB= new ReentrantLock();
    private Lock lockC= new ReentrantLock();

    public static void main(String[] args) {

    }

}
