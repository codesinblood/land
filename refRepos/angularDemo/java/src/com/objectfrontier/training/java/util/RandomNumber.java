package com.objectfrontier.training.java.util;

import java.util.Random;

public class RandomNumber {

    public static void main(String[] args) {

        // generating a random number on every run
        generatingRandomNumber();
    }

    private static void generatingRandomNumber() {

        Random number = new Random();

        System.out.println("The random integer value generated is : " + number.nextInt());
        System.out.println("The random long value generated is : " + number.nextLong());
        System.out.println("The random float value generated is : " + number.nextFloat());
        System.out.println("The random double value generated is : " + number.nextDouble());
    }
}
