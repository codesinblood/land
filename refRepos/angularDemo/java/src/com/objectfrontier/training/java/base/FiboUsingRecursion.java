package com.objectfrontier.training.java.base;
public class FiboUsingRecursion {

    int f1,f2,i;

    FiboUsingRecursion() {

        f1 = -1;
        f2 = 1;
        i = 0;
    }

    void fib(int length) {

        if(length != 0){
            int temp = f1 + f2;
            f1 = f2;
            f2 = temp;
            System.out.println(temp);
            i++;
            fib(length - 1);
        }
    }

    public static void main(String[] args) {

        FiboUsingRecursion obj = new FiboUsingRecursion();
        obj.fib(8);
    }
}
