package com.xiaocai.springboot.integration;

import java.io.*;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/28 11:06
 */
public class Test {

    public static void main(String[] args) {
        String   a ="abc";
//        System.out.println(a.substring(0,2));
        int count=0;
        Reader fileReader;
        try {
             fileReader = new BufferedReader(new FileReader("D:aa.txt"));
            char[] chars = new char[1024];
            int length=0;
            String s ="";
            while ((length=fileReader.read(chars))!=-1) {
                String temp = new String(chars,0,length);
                s+=temp;
            }

            while (true){
                int index=0;
                if (s.indexOf(a,index)!=-1) {
                    count ++;
                    s=s.substring(s.indexOf(a)+a.length());
                } else {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println(count);
        }
    }
}
