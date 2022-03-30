package com.xiaocai.springboot.javase.io.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * 服务器端在监听客户端连接时(serverSocket.accept ())，服务器端处于阻塞状态，不能处理其他事务
 * 服务器端需要为每个客户端建立一个线程，虽然可以用线程池来优化，但在并发较大时，线程开销依旧很大
 * 当连接的客户端没有发送数据时，服务器端会阻塞在read操作上，等待客户端输入，造成线程资源浪费
 * @author: xiaocai
 * @time: 2022/3/27 18:52
 */
public class BioServerDemo {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        System.out.println("服务器已启动");

        while (true){
            System.out.println("等待客户端连接.....（阻塞中）");
            Socket socket = serverSocket.accept();
            System.out.println("客户端连接");
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    handler(socket);
                }
            });
        }
    }

    private static void handler(Socket socket) {
        try{
            InputStream inputStream = socket.getInputStream();
            byte[] b = new byte[1024];
            while (true){
                System.out.println("等待客户端输入.....（阻塞中）");
                int read = inputStream.read(b);
                if (read != -1){
                    System.out.println(new String(b, 0, read));
                }else {
                    break;
                }
            }
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
