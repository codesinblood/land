package com.objectfrontier.training.java.base;
public class WrapOverload {

    void overload(Integer a, Double d) {
       System.out.println("Integer = " + a);
       System.out.println("Double = " + d);
    }

    void overload(Integer a, Float f) {
    System.out.println("Integer = " + a);
    System.out.println("Float = " + f);
    }

    public static void main(String[] args) {

        WrapOverload wrap = new WrapOverload();
        wrap.overload(50, 10.10);
        wrap.overload(10, 5.5f);
    }
}
