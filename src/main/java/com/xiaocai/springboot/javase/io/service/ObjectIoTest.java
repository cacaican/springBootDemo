package com.xiaocai.springboot.javase.io.service;

import com.xiaocai.springboot.javase.invoke.entity.Student;

import java.io.*;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/26 21:55
 */
public class ObjectIoTest {

    public static void main(String[] args) {

        Student student = new Student();
        student.setName("xiaocai");
        student.setAge(12);
        testObjectIo(student);


    }
     static  void testObjectIo(Object object) {
         try {
             System.out.println(object);

             ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:aaa.txt"));
             objectOutputStream.writeObject(object);

             ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:aaa.txt"));
             Object o = objectInputStream.readObject();
             System.out.println(o);
             System.out.println(o==object);
         } catch (IOException | ClassNotFoundException e) {
             e.printStackTrace();
         }
     }
}
