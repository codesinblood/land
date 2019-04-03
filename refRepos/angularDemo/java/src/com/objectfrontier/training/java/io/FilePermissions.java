// 12. Write a program get the permissions allowed for a file

package com.objectfrontier.training.java.io;

import java.io.File;

public class FilePermissions {

    public static void main(String[] args) {

        File file = new File("D:\\dev\\training\\vinoth.ari\\java\\xanadu.txt");

        if(file.isFile()) {

            if(file.canExecute()) {
                System.out.println("The File has Exceute Permissions");
            }
            if(file.canRead()) {
                System.out.println("The File has Read Permissions");
            }
            if(file.canWrite()) {
                System.out.println("The File has Write Permissions");
            }
        } else {
            System.out.println("The given file is invalid");
        }
    }
}
