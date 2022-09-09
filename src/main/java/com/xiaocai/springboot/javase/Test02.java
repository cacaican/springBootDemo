package com.xiaocai.springboot.javase;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/5/10 15:03
 */
public class Test02 {

    public static void main(String[] args) {

        A02 a02 = new A02();
        a02.testA02();

    }
}
class A02 {
    void testA02() {

        if (1!=1) {
            throw  new NullPointerException();
        }
        throw  new NullPointerException();

    }
}
class B02 {

}
