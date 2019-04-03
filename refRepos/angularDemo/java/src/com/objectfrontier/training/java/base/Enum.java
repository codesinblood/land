package com.objectfrontier.training.java.base;
public class Enum {

    public enum Day {

        Sun,
        Mon,
        Tue,
        Wed,
        Thu,
        Fri,
        Sat
    }

    public static void main(String[] args) {

        Day d1 = Day.Sun;
        Day d2 = Day.Mon;
        Day d3 = Day.Mon;
        Day d4 = Day.Tue;
        d1 = Day.Wed;
        d1 = Day.valueOf("Sat");
        System.out.println(d2.equals (d3));
        System.out.println(d1.equals (d2));
        System.out.println(d1);
        System.out.println(d2 == d3);
    }
}
