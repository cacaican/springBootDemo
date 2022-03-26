package com.xiaocai.springboot.javase.io.service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/26 21:44
 */
public class FileIoTest {


    void readFile(String path) {
        File file = new File(path);
        FileReader fileReader ;
        while (true) {
            try {
                fileReader = new FileReader(file);

                FileWriter writer = new FileWriter("D:aaa.txt");
                char content[] =new char[1024];
                int length;
                while (!((length = fileReader.read()) >0)) {
                    writer.write(content,0,length);
                };




            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }

        }


    }
}
