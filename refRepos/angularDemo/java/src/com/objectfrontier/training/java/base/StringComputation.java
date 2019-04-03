package com.objectfrontier.training.java.base;
public class StringComputation {

    public static void main(String[] args) {

        String hannah = "Did Hannah see bees? Hannah did.";
        int strLength = hannah.length();

        System.out.println("Original String = " + hannah);
        System.out.println("String lengh = " + strLength);

        char ch = hannah.charAt(12);
        System.out.println("12th Index in the String = " + ch);

        int index = hannah.indexOf('b');
        System.out.println("b Index in the String = " + index);
    }
}
