package com.xiaocai.base.ThreadDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ThreadPoolDemo
 * @Description 介绍线程池的各个类型
 * @Author xiaocai
 * @Date 2022/3/1
 */
public class ThreadPoolDemo {


    public static void main(String[] args) {

    }
}
class A extends Thread{
    public static void main(String[] args) {
        //获取当前项目可获取的线程数
        System.out.println(Runtime.getRuntime().availableProcessors());

    }

}

/*创建一个可根据需要创建新线程的线程池，
但是在以前构造的线程可用时将重用它们。
对于执行很多短期异步任务的程序而言，
这些线程池通常可提高程序性能。
调用 execute 将重用以前构造的线程（如果线程可用）。
如果现有线程没有可用的，则创建一个新线程并添加到池中。
终止并从缓存中移除那些已有 60 秒钟未被使用的线程。
因此，长时间保持空闲的线程池不会使用任何资源
*/
 class ThreadPoolExecutorTest {
    public static void main(String[]  args ) {
        ExecutorService cacheThreadPool = Executors.newCachedThreadPool();
        for(int i =1;i<=5;i++){
            final int index=i ;
            try{
                Thread.sleep(500);
            }catch(InterruptedException  e ) {
                e.printStackTrace();
            }
            cacheThreadPool.execute(new Runnable(){
                @Override
                public void run() {
                    System.out.println("第" +index +"个线程" +Thread.currentThread().getName());
                }
            });
        }
    }
}
