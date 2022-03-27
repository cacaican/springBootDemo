package com.xiaocai.springboot.javase;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/27 15:57
 */
public class Test {

    public static void main(String[] args) {
        A a = new A();
        a.name="xiaocai";
        System.out.println(a);
        testChangeA(a);
        System.out.println(a);

        String s = new String("123");
        StringBuilder builder = new StringBuilder(s);
        StringBuilder reverse = builder.reverse();
    }

    private static void testChangeA(A a) {
        a.name ="天才";
    }
}
class A {
    public String name;

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                '}';
    }
}