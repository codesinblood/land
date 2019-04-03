package com.objectfrontier.training.java.base;
public class FiboUsingWhile {

    int f1,f2,i;

    FiboUsingWhile() {

        f1 = -1;
        f2 = 1;
        i = 0;
    }

    void fib(int length) {

        while(i <= length) {
            int temp = f1 + f2;
            f1 = f2;
            f2 = temp;
            System.out.println(temp);
            i++;
        }
    }

    public static void main(String[] args) {

        FiboUsingFor obj = new FiboUsingFor();
        obj.fib(8);
    }
}
