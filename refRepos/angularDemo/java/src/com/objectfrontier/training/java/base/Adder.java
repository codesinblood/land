package com.objectfrontier.training.java.base;
public class Adder {

    public static void main(String[] args) {

        int sum = 0;
        if(args.length == 1){
            System.out.println("You enter only one input.");
        } else {
            for(String temp : args) {
                sum += Integer.parseInt(temp);
            }
            System.out.println("Sum is " + sum);
        }
    }
}
