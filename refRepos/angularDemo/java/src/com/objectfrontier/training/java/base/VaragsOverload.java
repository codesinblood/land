package com.objectfrontier.training.java.base;
public class VaragsOverload {

    void var(int ...a) {

        System.out.println("Number of arguments is: " + a.length);
        for(int i: a){
            System.out.println(i);
        }
    }

    void var(String ...str) {

        System.out.println("Number of arguments is: " + str.length);
        for(String i: str) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {

        VaragsOverload varags = new VaragsOverload();
        varags.var("Hi","Vinoth","!!!!");
        varags.var(1, 2, 3, 4, 5);
    }
}
