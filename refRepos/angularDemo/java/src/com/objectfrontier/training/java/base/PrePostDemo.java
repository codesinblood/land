package com.objectfrontier.training.java.base;
public class PrePostDemo {

    public static void main(String[] args){

        int i = 3;
        i++;
        System.out.println(i);    // "4"
        ++i;
        System.out.println(i);    // "5"
        System.out.println(++i);  // "6"
        System.out.println(i++);  // "6" Because it is a post increment.
        System.out.println(i);    // "7"
    }
}
