package com.objectfrontier.training.java.base;
public class NumberHolder {

    public int anInt;
    public float aFloat;

    NumberHolder(int a, float b) {

        anInt = a;
        aFloat = b;
        System.out.println(anInt + " " + aFloat);
    }

    public static void main(String[] args) {

        NumberHolder num = new NumberHolder(10, 7.77f);
    }
}
