package com.objectfrontier.training.java.base;

import java.util.Map;
import java.util.TreeMap;

public class Builder {

    public static void main(String args[]) {

        StringBuilder initialCapacity = new StringBuilder("Able was I ere I saw Elba.");
        StringBuilder emptyCapacity = new StringBuilder();
        System.out.println("StringBuilder Empty Capacity = " + emptyCapacity.capacity());
        System.out.println("StringBuilder Initial Capacity = " + initialCapacity.capacity());
        char[] arr = {};
        System.out.println(arr);
//        String s1 = new String(arr);
        System.out.println('h' + "aHa");
//        String s2 = 'h' + 'a' ;
        char a = 'a';
        char b = 'b';
        System.out.println(1 + 2 + "d");
        StringBuilder emptyCapacity1 = new StringBuilder()
                .append(1)
                .append('a');
        System.out.println(emptyCapacity1);
        String length = "length";
        String size = "length";
        if(length == size) {
            System.out.println(true);
        }

        System.out.println(length);
        System.out.println(size);
        System.out.println(size.hashCode() + "" + length.hashCode());
        System.out.println("is animal equal to " + length.equals(size));
        System.out.println("is animal equal to " + (length == size));
    Map<Integer, Integer> map = new TreeMap<>();
    Integer one = new Integer(1);
    Integer anotherOne = new Integer(1);

    map.put(1, 3);
    map.put(1,  6);
    System.out.println(map.get(1));
    }
}
