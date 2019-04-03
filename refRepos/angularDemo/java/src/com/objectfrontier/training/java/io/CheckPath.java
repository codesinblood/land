package com.objectfrontier.training.java.io;

import java.io.File;
import java.io.IOException;


public class CheckPath {

    public static void main(String[] args) throws IOException {


        File file = new File("D:\\dev\\training\\vinoth.ari\\java\\");

        if (file.isDirectory()) {
            System.out.println("The given pathname is a directory");
        }
        else if (file.isFile()) {
            System.out.println("The given pathname is a file");
        } else {
            System.err.println("The given pathname is not valid");
        }

//        Path path = Paths.get("D:\\dev\\training\\vinoth.ari\\java\\sample");
//
//        BufferedReader buffer = Files.newBufferedReader(path);
//
//        System.out.println(Files.isRegularFile(path));
//
//        System.out.println(Files.isDirectory(path));
    }
}
