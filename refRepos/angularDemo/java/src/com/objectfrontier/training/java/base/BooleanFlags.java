package com.objectfrontier.training.java.base;
public class BooleanFlags {

    void flag(int num) {

       boolean flag1,flag2,flag3,flag4;
       System.out.println(flag1 = (num == 15));
       System.out.println(flag2 = (num < 10));
       System.out.println(flag3 = (num <= 20));
       System.out.println(flag4 = (num != 15));
    }
    public static void main(String[] args) {

        BooleanFlags obj = new BooleanFlags();
        obj.flag(15);
    }
}
