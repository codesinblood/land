package com.objectfrontier.training.java.base;
public class FiboUsingFor {

    int f1,f2;

    FiboUsingFor() {

        f1 = -1;
        f2 = 1;
    }

    void fib(int length) {

        for(int i = 1;i <= length;i++) {
            int temp = f1 + f2;
            f1 = f2;
            f2 = temp;
            System.out.println(temp);
        }
    }

    public static void main(String[] args) {

        FiboUsingFor obj = new FiboUsingFor();
        obj.fib(8);
    }
}
