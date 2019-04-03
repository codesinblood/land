// 11. Write a program to get the number of files and number of directories in a given directory

package com.objectfrontier.training.java.io;

import java.io.File;

public class ListFiles {

    public static void main(String[] args) {

        File filePath = new File("D:\\dev\\training\\vinoth.ari\\java\\");

        System.out.println("The number of files and directories are " + filePath.list().length);

        String[] files1 = filePath.list();
        for (String file : files1) {
            System.out.println(file);
        }

//        File[] files2 = filePath.listFiles();
//        for (File file : files2) {
//            System.out.println(file);
//        }
    }
}
