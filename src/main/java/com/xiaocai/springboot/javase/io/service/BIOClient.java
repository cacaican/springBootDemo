package com.xiaocai.springboot.javase.io.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/27 18:56
 */
public class BIOClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 6666);
        OutputStream outputStream = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String message = scanner.nextLine();
            if ("exit".equals(message)) {
                break;
            }
            outputStream.write(message.getBytes());
        }
        outputStream.close();
        socket.close();
    }
}
