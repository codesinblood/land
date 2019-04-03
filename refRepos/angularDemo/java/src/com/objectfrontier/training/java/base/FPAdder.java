package com.objectfrontier.training.java.base;
public class FPAdder {

    public static void main(String[] args) {

        float sum = 0;
        if(args.length == 1){
            System.out.println("You enter only one input.");
        } else {
            for(String a : args) {
                sum += Float.parseFloat(a);
            }
            System.out.println(String.format("%.2f",sum));
        }
    }
}
