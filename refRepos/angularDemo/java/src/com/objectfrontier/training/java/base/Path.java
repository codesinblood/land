package com.objectfrontier.training.java.base;
public class Path {

    public static void main(String[] args) {

        String path = Path.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.out.println("Absolute Path Is" + path);
    }
}
