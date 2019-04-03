package com.objectfrontier.training.java.util;

import java.util.Objects;

public class NonNullObjectCheck {

    public static void main(String[] args) {

        // creating a object and check whether it is null or non null
        run();
    }

    private static void run() {

        NonNullObjectCheck object = new NonNullObjectCheck();

        System.out.println(Objects.nonNull(object));
        System.out.println(Objects.isNull(object));
    }
}
