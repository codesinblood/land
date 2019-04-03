package com.objectfrontier.training.java.base;
/*
public interface SomethingIsWrong {
    void aMethod(int aValue){
        System.out.println("Hi Mom");
    }
}
*/

interface SomethingIsWrong1 {

    void aMethod(int aValue);
}

public class SomethingIsWrongInInterface implements SomethingIsWrong1 {

    @Override
    public void aMethod(int aValue) {

        System.out.println("Hi Mom");
        System.out.println(aValue);
    }

    public static void main(String[] args) {

        SomethingIsWrongInInterface obj = new SomethingIsWrongInInterface();
        obj.aMethod(100);
    }
}